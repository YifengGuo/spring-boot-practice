package com.yifeng.spring_boot_practice.chapter4_aop.reflection;

/**
 * Created by guoyifeng on 10/10/19
 */

import java.lang.reflect.Constructor;

/**
 * creating new instance via reflection
 * this skill is the foundation of java proxy design pattern
 */
public class InvokeConstructor_4 {
    static class Person {

        private String name;

        public Person() {}

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public void hello() {
            System.out.println("Hello: Person");
        }

        @Override
        public String toString() {
            return "Person:" + name;
        }
    }

    public static void main(String[] args) throws Exception {
        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        // 调用构造方法:
        Integer n1 = (Integer) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer) cons2.newInstance("456");
        System.out.println(n2);

        // 调用Class.newInstance()的局限是，它只能调用该类的public无参数构造方法。
        // 如果构造方法带有参数，或者不是public，就无法直接通过Class.newInstance()来调用。
        Person p = Person.class.newInstance();
        System.out.println(p);

        Constructor cons3 = Person.class.getConstructor(String.class);
        Person p2 = (Person) cons3.newInstance("Yifeng");
        System.out.println(p2);
    }
}
