package com.yifeng.spring_boot_practice.chapter4_aop.reflection;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by guoyifeng on 10/10/19
 */
public class ObtainInheritance_5 {
    public static void main(String[] args) {
        // given Class obj, we can access to its superclass
        Class i = Integer.class;
        Class n = i.getSuperclass(); // Number
        System.out.println(n);
        Class o = n.getSuperclass(); // Object
        System.out.println(o);
        System.out.println(o.getSuperclass()); // null

        // obtain interfaces
        Class s = Integer.class;
        Class s2 = Integer.class.getSuperclass();
        Class[] is = s.getInterfaces();
        Class[] sis = s2.getInterfaces();
        // getInterfaces()只返回当前类直接实现的接口类型，并不包括其父类实现的接口类型
        for (Class clz : is) {
            System.out.println(clz);
        }

        for (Class clz : sis) {
            System.out.println(clz);
        }

        // 对所有interface的Class调用getSuperclass()返回的是其父interface或者null
        // this lambda actually creates an instance, so it inherits from Object
        Closeable closeable = () -> {

        };
        System.out.println(closeable.getClass().getSuperclass());  // Object
        System.out.println(java.io.Closeable.class.getInterfaces()[0]); // AutoCloseable，因为Closeable继承自AutoCloseable
        System.out.println(Runnable.class.getSuperclass()); // null，因为Runnable没有继承自其他接口
        System.out.println(java.util.Queue.class.getSuperclass());  // interface调用getSuperclass()只会返回空，因为interface不会从某个类继承
    }
}
