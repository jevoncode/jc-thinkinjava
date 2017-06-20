package com.jc.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 兼容未使用泛型的代码，不过也只是运行时发现错误而已
 * @author jevoncode
 *
 */
class Pet{
	
}
class Dog extends Pet{
	
}
class Cat extends Pet{
	
}

public class CheckedList {
	@SuppressWarnings("unchecked")
	static void oldStyleMethod(List probablyDogs) {
		probablyDogs.add(new Cat());
	}

	public static void main(String[] args) {
		List<Dog> dogs1 = new ArrayList<Dog>();
		oldStyleMethod(dogs1); // Quietly accepts a Cat
		List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
		try {
			oldStyleMethod(dogs2); // Throws an exception
		} catch (Exception e) {
			System.out.println(e);
		}
		// Derived types work fine:
		List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
		pets.add(new Dog());
		pets.add(new Cat());
	}
}