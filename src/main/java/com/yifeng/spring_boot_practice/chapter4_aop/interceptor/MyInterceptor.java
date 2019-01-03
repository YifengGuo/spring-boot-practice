package com.yifeng.spring_boot_practice.chapter4_aop.interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by guoyifeng on 12/22/18
 */
public class MyInterceptor implements Interceptor {
    public boolean before() {
        System.out.println("Before...");
        return true;
    }

    public void after() {
        System.out.println("After...");
    }

    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Around before");  // before the invocation of target's method
        Object obj = invocation.proceed();
        return obj;
    }

    public void afterReturning() {
        System.out.println("After returning...");
    }

    public void afterThrowing() {
        System.out.println("After throwing...");
    }

    public boolean useAround() {
        return true;
    }
}
