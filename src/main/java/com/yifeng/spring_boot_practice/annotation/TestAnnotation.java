package com.yifeng.spring_boot_practice.annotation;

/**
 * Created by guoyifeng on 10/11/19
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Meta Annotation: annotation for annotations
 *
 * 1. @Target
 * 最常用的元注解是@Target。使用@Target可以定义Annotation能够被应用于源码的哪些位置：
 * 类或接口：ElementType.TYPE；
 * 字段：ElementType.FIELD；
 * 方法：ElementType.METHOD；
 * 构造方法：ElementType.CONSTRUCTOR；
 * 方法参数：ElementType.PARAMETER。
 *
 * e.g:
 * @Target(ElementType.METHOD)
 *
 * @Target({
 *     ElementType.METHOD,
 *     ElementType.FIELD
 * })
 * public @interface TestAnnotation {
 * }
 *
 * 实际上@Target定义的value是ElementType[]数组，只有一个元素时，可以省略数组的写法。
 *
 *
 * 2. @Retention
 * 元注解@Retention定义了Annotation的生命周期：
 *
 * 仅编译期：RetentionPolicy.SOURCE；在编译期就被丢掉了；
 * 仅class文件：RetentionPolicy.CLASS；仅保存在class文件中，它们不会被加载进JVM；
 * 运行期：RetentionPolicy.RUNTIME。会被加载到JVM，并且在运行期可以被程序读取。
 * 如果@Retention不存在，则该Annotation默认为CLASS。
 * 因为通常我们自定义的Annotation都是RUNTIME，所以，务必要加上@Retention(RetentionPolicy.RUNTIME)这个元注解
 *
 * 3. @Repeatable
 * 使用@Repeatable这个元注解可以定义Annotation是否可重复。这个注解应用不是特别广泛。
 *
 * 4. @Inherited
 * 使用@Inherited定义子类是否可继承父类定义的Annotation。@Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效，并且仅针对class的继承，对interface的继承无效：
 *
 */

/**
 * A simple definition for an annotation
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    int type() default 0;

    String value() default "";

    String level() default "info";
}
