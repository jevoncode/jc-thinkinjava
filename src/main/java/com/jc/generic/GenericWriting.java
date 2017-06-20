package com.jc.generic;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * 泛型方法是用超类通配符的好处例子，能使用上转型的好处
 * @author jevoncode
 */
public class GenericWriting {
	static <T> void writeExact(List<T> list, T item) {
		list.add(item);
	}

	static List<Apple> apples = new ArrayList<Apple>();
	static List<Fruit> fruit = new ArrayList<Fruit>();

	static void f1() {
		writeExact(apples, new Apple());
		// writeExact(fruit, new Apple()); // Error:
		// Incompatible types: found Fruit, required Apple
	}

	static <T> void writeWithWildcard(List<? super T> list, T item) {
		list.add(item);
	}

	static void f2() {
		writeWithWildcard(apples, new Apple());
		writeWithWildcard(fruit, new Apple());
	}

	public static void main(String[] args) {
		f1();
		f2();
	}
}