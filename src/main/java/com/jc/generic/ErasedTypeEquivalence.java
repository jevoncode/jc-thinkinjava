package com.jc.generic;

import java.util.ArrayList;

/**
 * java泛型和c++的模版的区别，java泛型有擦去，这个例子展示泛型虽然行为不同但类是同一个
 * @author jevoncode
 *
 */
public class ErasedTypeEquivalence {
	public static void main(String[] args) {
		Class c1 = new ArrayList<String>().getClass();
		Class c2 = new ArrayList<Integer>().getClass();
		System.out.println(c1 == c2);
	}
}