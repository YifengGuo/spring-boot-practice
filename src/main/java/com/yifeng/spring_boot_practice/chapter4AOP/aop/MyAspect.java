package com.yifeng.spring_boot_practice.chapter4AOP.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by guoyifeng on 12/30/18
 */

/**
 * This is customized aspect for join point.
 * It defines the interception logic for the target's method
 */
@Aspect
public class MyAspect {
    /**
     * UserServiceImpl will be enhanced by UserValidatorImpl.class
     */
    @DeclareParents(value = "com.yifeng.spring_boot_practice.chapter4AOP.aop.UserServiceImpl",
            defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Pointcut("execution(* com.yifeng.spring_boot_practice.chapter4AOP.aop.UserServiceImpl.printUser(..))")
    public void pointCut(){

    }

    /**
     *
     * @param jp join point (method of target)
     * @param user the args of join point's (target's) method  (here method's param is a User obeject)
     */
    @Before("pointCut() && args(user)")
    public void beforeParam(JoinPoint jp, User user) {
        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
        System.out.println("Before...");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("After...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("AfterThrowing...");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("AfterReturning...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Around Before...");
        jp.proceed(); // callback of target's method
        System.out.println("Around After...");
    }
}
