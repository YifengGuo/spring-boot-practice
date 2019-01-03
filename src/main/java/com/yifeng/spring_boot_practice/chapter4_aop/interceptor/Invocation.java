package com.yifeng.spring_boot_practice.chapter4_AOP.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by guoyifeng on 12/22/18
 */
public class Invocation {

    private Object target;

    private Method method;

    private Object[] params;

    public Invocation(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    /**
     * invoke the target's method via reflection
     * target: the object which the proxy works for
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed () throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }

    public Object[] getParams() {
        return params;
    }

    public Method getMethod() {
        return method;
    }

    public Object getTarget() {
        return target;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
