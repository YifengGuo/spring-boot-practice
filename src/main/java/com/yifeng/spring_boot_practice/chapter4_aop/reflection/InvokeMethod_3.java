package com.yifeng.spring_boot_practice.chapter4_aop.reflection;

/**
 * Created by guoyifeng on 10/10/19
 */

import java.lang.reflect.Method;

/**
 * invoke class public or private methods via reflection
 */
public class InvokeMethod_3 {
    static class Student extends Person {
        public int getScore(String type) {
            return 99;
        }
        private int getGrade(int year) {
            return 1;
        }

        @Override
        public void hello() {
            System.out.println("Hello: Student");
        }
    }

    static class Person {

        private String name;

        public String getName() {
            return this.name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public void hello() {
            System.out.println("Hello: Person");
        }
    }

    public static void main(String[] args) throws Exception {
        /**
         * Method object:
         * getName()：返回方法名称，例如："getScore"；
         * getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
         * getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
         * getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
         */
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));

        // invoking method via reflection
        String s = "hello world";
        String sub = s.substring(6); // "world"
        Class clz = s.getClass();
        Method m = clz.getMethod("substring", int.class);
        Object res = m.invoke(s, 6);
        System.out.println(sub.contentEquals((String) res));

        // invoking static methods via reflection
        Method m2 = Integer.class.getMethod("parseInt", String.class);
        Integer i2 = (Integer) m2.invoke(null, "12345");  // for static, Object instance is set to null
        System.out.println(i2);

        // invoking private methods via reflection
        Person p = new Person();
        Method privateMethod = Person.class.getDeclaredMethod("setName", String.class);
        privateMethod.setAccessible(true);  // make private methods accessible
        privateMethod.invoke(p, "Yifeng");
        System.out.println(p.getName());

        // method invoking in inheritance
        Method h = Person.class.getMethod("hello");
        h.invoke(new Student()); // follow polymorphism
    }
}
