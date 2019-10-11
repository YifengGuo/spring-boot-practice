package com.yifeng.spring_boot_practice.annotation;

/**
 * Created by guoyifeng on 10/11/19
 */

/**
 * A simple customized annotation
 * followed by several api tests
 */

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * 如何使用注解完全由工具决定。SOURCE类型的注解主要由编译器使用，因此我们一般只使用，不编写。
 * CLASS类型的注解主要由底层工具库使用，涉及到class的加载，一般我们很少用到。
 * 只有RUNTIME类型的注解不但要使用，还经常需要编写。
 * 因此，我们只讨论如何读取RUNTIME类型的注解。
 *
 * 因为注解定义后也是一种class，所有的注解都继承自java.lang.annotation.Annotation，因此，读取注解，需要使用反射API。
 *
 * 判断某个注解是否存在于Class、Field、Method或Constructor：
 * Class.isAnnotationPresent(Class)
 * Field.isAnnotationPresent(Class)
 * Method.isAnnotationPresent(Class)
 * Constructor.isAnnotationPresent(Class)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {

    int type() default 0;

    String value() default "";

    String level() default "info";
}

public class Report {
    public static void main(String[] args) throws Exception {
        // 获取单个注解
        MyAnnotation myAnnotation = Person.class.getAnnotation(MyAnnotation.class);
        if (myAnnotation != null) {
            String value = myAnnotation.value();
            String level = myAnnotation.level();
            System.out.println(value + " " + level);
        }

        // 获取方法参数（多个）注解
        Method method = Person.class.getMethod("hello", String.class, String.class);
        Annotation[][] annos = method.getParameterAnnotations();
        // 第一个参数（索引为0）的所有Annotation:
        Annotation[] annosOfName = annos[0];
        for (Annotation anno : annosOfName) {
            if (anno instanceof Range) { // @Range注解
                Range r = (Range) anno;
                System.out.println(r.max());
            }
            if (anno instanceof NotNull) { // @NotNull注解
                NotNull n = (NotNull) anno;
                System.out.println(n.message());
            }
        }
    }

    @MyAnnotation(value = "haha", level = "debug")
    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public void hello(@NotNull @Range(max=5) String name, @NotNull String prefix) {

        }
    }
}
