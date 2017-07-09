package com.jc.enumerated.menu;

import com.jc.enumerated.util.Enums;
/**
 * 用接口去组织枚举——枚举的枚举
 * @author jevoncode
 *
 */
public enum Course {
	APPETIZER(Food.Appetizer.class), MAINCOURSE(Food.MainCourse.class), DESSERT(Food.Dessert.class), COFFEE(
			Food.Coffee.class);
	private Food[] values;

	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}
}