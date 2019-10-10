package com.yifeng.spring_boot_practice.chapter4_aop.reflection;

import java.lang.reflect.Field;

/**
 * Created by guoyifeng on 10/9/19
 */
public class AccessFields_2 {

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Student extends Person {
        private String name;
        public Student(String name) {
            super(name);
        }

        public int score;
        private int grade;
    }

    /**
     * 一个Field对象包含了一个字段的所有信息：
     * f.getName()：返回字段名称，例如，"name"；
     * f.getType()：返回字段类型，也是一个Class实例，例如，String.class；
     * f.getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
     *
     * Field f = String.class.getDeclaredField("value");
     * f.getName(); // "value"
     * f.getType(); // class [B 表示byte[]类型
     * int m = f.getModifiers();
     * Modifier.isFinal(m); // true
     * Modifier.isPublic(m); // false
     * Modifier.isProtected(m); // false
     * Modifier.isPrivate(m); // true
     * Modifier.isStatic(m); // false
     */
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getDeclaredField("name"));
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));

        // 获取字段的值
        Object p = new Person("Xiao Ming");
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true); // make private fields accessible
        Object value = f.get(p);
        System.out.println(value); // "Xiao Ming"

        // set value via reflection
        f.set(p, "Xiao Hong");
        System.out.println((((Person) p).getName())); // "Xiao Hong"

    }
}
