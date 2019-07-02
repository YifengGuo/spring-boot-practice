package com.yifeng.spring_boot_practice.chapter4_aop.proxy_basics;

/**
 * Created by guoyifeng on 6/13/19
 */
interface TestInterface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements TestInterface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}

class SimpleProxy implements TestInterface {

    private TestInterface proxied;  // target to be proxied

    public SimpleProxy(TestInterface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
    }
}

public class SimpleProxyDemo {
    public static void consumer(TestInterface i) {
        i.doSomething();
        i.somethingElse("barfoo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        System.out.println();
        consumer(new SimpleProxy(new RealObject())); // RealObject is proxied by SimpleProxy
    }
}
