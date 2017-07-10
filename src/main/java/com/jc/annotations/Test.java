package com.jc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，也被称为元数据（metadata）
 * @author jevoncode
 *  @Target定义应用到类的什么位置
 *  @Retention定义哪个级别可以使用，有源代码（SOURCE）、类文件（CLASS）或者是运行时（RUNTIME）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

}
