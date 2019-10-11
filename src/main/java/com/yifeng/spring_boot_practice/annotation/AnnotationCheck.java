package com.yifeng.spring_boot_practice.annotation;

/**
 * Created by guoyifeng on 10/11/19
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Define a simple annotation used for checking validity of Person fields
 */
public class AnnotationCheck {
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Range {
        int min() default 10;

        int max() default 255;
    }

    static class Person {
        @Range(min = 1, max = 20)
        private String name;

        @Range(min = 18, max = 60)
        private int age;

        @Range(min = 1, max = 20)
        private String city;

        public Person(String name, String city, int age) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return String.format("{Person: name=%s, city=%s, age=%d}", name, city, age);
        }
    }

    public static void check(Person p) throws IllegalAccessException, IllegalArgumentException{
        for (Field field : p.getClass().getDeclaredFields()) {  // must getDeclaredFields() otherwise private fields are not accessible
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                field.setAccessible(true); // make private fields accessible
                Object value = field.get(p);
                if (value instanceof String) {
                    if (((String) value).length() < range.min() || ((String) value).length() > range.max()) {
                        throw new IllegalArgumentException();
                    }
                }
                if (value instanceof Integer) {
                    if ((Integer) value < range.min() || (Integer) value > range.max()) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Person p1 = new Person("Bob", "Beijing", 20);
        Person p2 = new Person("", "Shanghai", 20);
        Person p3 = new Person("Alice", "Shanghai", 199);
        for (Person p : new Person[] { p1, p2, p3 }) {
            try {
                check(p);
                System.out.println("Person " + p + " checked ok.");
            } catch (IllegalArgumentException e) {
                System.out.println("Person " + p + " checked failed: " + e);
            }
        }
    }
}
