package com.yifeng.spring_boot_practice.chapter4_aop.interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by guoyifeng on 12/22/18
 */
public interface Interceptor {

    // before target's method
    boolean before();

    // after target's method
    void after();

    /**
     * method to replace method of Target
     * @param invocation callback param, proceed() of Invocation can call back target's method
     * @return the result of target's method
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    // return method when no exception thrown
    void afterReturning();

    // return method when some exception thrown
    void afterThrowing();

    // if replace target's method with around()
    boolean useAround();
}
