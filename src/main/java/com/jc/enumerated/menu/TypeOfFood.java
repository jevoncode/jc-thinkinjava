package com.jc.enumerated.menu;

import com.jc.enumerated.menu.Food.Appetizer;
import com.jc.enumerated.menu.Food.Coffee;
import com.jc.enumerated.menu.Food.Dessert;
import com.jc.enumerated.menu.Food.MainCourse;

public class TypeOfFood {
	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.LASAGNE;
		food = Dessert.GELATO;
		food = Coffee.CAPPUCCINO;
	}
}
