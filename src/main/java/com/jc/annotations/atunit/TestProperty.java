package com.jc.annotations.atunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Java编程思想里的 单元测试的框架
 * @author jevoncode
 *
 */
//Both fields and methods may be tagged as properties:
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TestProperty {
}