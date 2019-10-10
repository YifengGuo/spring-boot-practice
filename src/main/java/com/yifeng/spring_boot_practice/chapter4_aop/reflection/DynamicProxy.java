package com.yifeng.spring_boot_practice.chapter4_aop.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by guoyifeng on 10/10/19
 */
public class DynamicProxy {

    /**
     * 比较Java的class和interface的区别：
     *
     * 可以实例化class（非abstract）；
     * 不能实例化interface。
     * 所有interface类型的变量总是通过向上转型并指向某个实例的：
     * CharSequence cs = new StringBuilder();
     */

    /**
     * 有没有可能不编写实现类，直接在运行期创建某个interface的实例呢？
     * 这是可能的，因为Java标准库提供了一种动态代理（Dynamic Proxy）的机制：可以在运行期动态创建某个interface的实例。
     */

    /**
     * 静态代理
     */
    interface Hello {
        void morning(String name);
    }

    static class HelloImpl implements Hello {
        @Override
        public void morning(String name) {
            System.out.println("Good morning " + name);
        }
    }

    /**
     * 还有一种方式是动态代码，我们仍然先定义了接口Hello，
     * 但是我们并不去编写实现类，而是直接通过JDK提供的一个Proxy.newProxyInstance()创建了一个Hello接口对象。
     * 这种没有实现类但是在运行期动态创建了一个接口对象的方式，我们称为动态代码。
     * JDK提供的动态创建接口对象的方式，就叫动态代理。
     *
     * 在运行期动态创建一个interface实例的方法如下：
     * 1. 定义一个InvocationHandler实例，它负责实现接口的方法调用，在此处编写实际的业务逻辑
     * 2. 通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
     *      2.1 使用的ClassLoader，通常就是接口类的ClassLoader；
     *      2.2 需要实现的接口数组，至少需要传入一个接口进去；
     *      2.3 用来处理接口方法调用的InvocationHandler实例。
     * 3. 将返回的Object强制转型为接口。
     */

    public static void main(String[] args) {
        // 静态代理
        // 向上转型创建接口实例并调用
        Hello hello = new HelloImpl();
        hello.morning("Bob");

        // simple dynamic proxy
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        Hello hello2 = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class }, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello2.morning("Bob");
    }

    /**
     * 动态代理实际上是JDK在运行期动态创建class字节码并加载的过程
     * 上述动态代理写成静态类如下：
     * 每次调用则要通过向上转型实例HelloDynamicProxy然后调用方法
     */
    class HelloDynamicProxy implements Hello {
        InvocationHandler handler;
        public HelloDynamicProxy(InvocationHandler handler) {
            this.handler = handler;
        }
        public void morning(String name) {
            try {
                handler.invoke(
                        this,
                        Hello.class.getMethod("morning"),
                        new Object[] { name });
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
