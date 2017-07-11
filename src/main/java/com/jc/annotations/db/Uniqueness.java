package com.jc.annotations.db;
/**
 * 根据实体生成创建表的sql
 * @author jevoncode
 *
 */
public @interface Uniqueness {
	Constraints constraints() default @Constraints(unique = true);
}
