package com.jc.annotations.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 根据实体生成创建表的sql
 * @author jevoncode
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
	int value() default 0;

	String name() default "";

	Constraints constraints() default @Constraints;
}