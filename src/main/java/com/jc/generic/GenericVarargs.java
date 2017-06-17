package com.jc.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型也可以用于可变参数中
 * @author jevoncode
 *
 */
public class GenericVarargs {
	public static <T> List<T> makeList(T... args) {
		List<T> result = new ArrayList<T>();
		for (T item : args)
			result.add(item);
		return result;
	}

	public static void main(String[] args) {
		List<String> ls = makeList("A");
		System.out.println(ls);
		ls = makeList("A", "B", "C");
		System.out.println(ls);
		ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
		System.out.println(ls);
	}
}