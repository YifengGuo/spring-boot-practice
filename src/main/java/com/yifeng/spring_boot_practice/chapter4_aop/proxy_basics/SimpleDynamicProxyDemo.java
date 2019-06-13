package com.yifeng.spring_boot_practice.chapter4_aop.proxy_basics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by guoyifeng on 6/13/19
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;  // target to be proxied

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    // implementations on how proxy inserts something into target object
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println("   " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}


public class SimpleDynamicProxyDemo {
    public static void consumer(Interface i) {
        i.doSomething();
        i.somethingElse("boofar");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();  // Object(methods) to be proxied

        consumer(real);
        System.out.println();
        // create a proxy upon RealObject
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(real)
        );
        consumer(proxy);
    }
}
