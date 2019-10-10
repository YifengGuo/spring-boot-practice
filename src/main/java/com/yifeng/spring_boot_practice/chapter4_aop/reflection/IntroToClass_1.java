package com.yifeng.spring_boot_practice.chapter4_aop.reflection;

/**
 * Created by guoyifeng on 9/17/19
 */

/**
 * Class instance was created by loaded class by JVM.
 * Constructor of Class is private so only JVM can create Class instance
 * Each Class instance contains info of class like interfaces,inheritance, fields, methods, etc.
 * getting class info via Class is called reflection
 */
public class IntroToClass_1 {
    public static void main(String[] args) {
        // Three ways to get Class
        // 这三种方式获取的Class实例都是同一个实例，因为JVM对每个加载的Class只创建一个Class实例来表示它的类型。
        // 1. get from static variable of class
        Class cls1 = String.class;
        String s1 = "hello";
        // 2. get from class instance method
        Class cls2 = s1.getClass();
        // 3. get from static method with full name of class
        try {
            Class cls3 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // differences between instanceof and three ways of getting class
        // instanceof also matches child classes of of current class
        Integer n = new Integer(123);

        boolean b1 = n.getClass() == Integer.class; // true
//        boolean b2 = n.getClass() == Number.class; // FALSE compile error

        boolean b3 = n instanceof Integer; // true
        boolean b4 = n instanceof Number; // true

        // given Class instance, one can create corresponding type instance
        // but newInstance() can only create type instance from its constructor which has NO parameter
        try {
            Class cls = String.class;
            String s = (String)cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // dynamic loading
        // for example:
        // JVM will first load Main.class into heap and only when program is executing create(),
        // JVM then will load Person.class into heap
//        public class Main {
//            public static void main(String[] args) {
//                if (args.length > 0) {
//                    create(args[0]);
//                }
//            }
//
//            static void create(String name) {
//                Person p = new Person(name);
//            }
//        }

    }
}
