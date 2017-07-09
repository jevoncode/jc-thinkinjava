package com.jc.enumerated.menu;

/**
 * 利用接口组织枚举和枚举的枚举来实现个一餐饭的例子
 * @author jevoncode
 *
 */
public class Meal {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			for (Course course : Course.values()) {
				Food food = course.randomSelection();
				System.out.println(food);
			}
			System.out.println("---");
		}
	}
}
