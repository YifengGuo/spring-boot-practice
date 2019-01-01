package com.yifeng.spring_boot_practice.chapter4AOP.interceptor;

/**
 * Created by guoyifeng on 12/22/18
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Weave the service and interceptor methods into corresponding pipeline by dynamic proxy pattern
 *
 * Demo convention:
 *  step 1: when using proxy, before() of interceptor would be invoked before invocation of proxy's method
 *  step 2: if useAround() return true -> invoke interceptor's around() instead of target's
 *                                        actually around() will invoke Invocation's proceed() which will also invoke target's method
 *          if useAround() return false -> use target's method
 *  step 3: after() will be invoked under any case
 *  step 4: when around() invoked, if exception throws, afterThrowing() will be invoked, otherwise, afterReturning will be invoked
 */
public class ProxyBean implements InvocationHandler {

    private Object target;  // the target object

    private Interceptor interceptor; // the interceptor object used in invoke()

    /**
     *
     * @param target the object which proxy works for
     * @param interceptor the convention holder
     * @return an object (proxy) which implemented target and has some method we need
     */
    public static Object getProxyBean(Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();

        proxyBean.target = target;

        proxyBean.interceptor = interceptor;

        /**
         * classloader of target
         * interfaces of target which need proxy to be bonded
         * InvocationHandler: to implement proxy logic
         */
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),  // return an array of interfaces so that different interfaces (targets) can be cast each other during Introduction
                proxyBean);

        return proxy;
    }

    /**
     * handle the logic of proxy
     * @param proxy proxy object
     * @param method the method of target
     * @param args the params of runtime
     * @return the result of method
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exceptionFlag = false;

        Invocation invocation = new Invocation(target, method, args);

        Object res = null;

        try {
            if (this.interceptor.before()) {
                res = this.interceptor.around(invocation);
            } else {
                res = method.invoke(target, args);
            }
        } catch (Exception e) {
            exceptionFlag = true;
        }
        this.interceptor.after();
        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return res;
        }
        return null;
    }

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("Yifeng");

        System.out.println("********** name is null **********");
        proxy.sayHello(null);
    }
}
