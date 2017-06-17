package com.jc.generic;

import java.util.ArrayList;
import java.util.Collection;

import com.jc.generic.coffee.Coffee;
import com.jc.generic.coffee.CoffeeGenerator;
import com.jc.generic.util.Generator;

/**
 * 利用泛型方法、泛型集合、泛型生成器，可以弄成一个通用的填充方法
 * @author jevoncode
 *
 */
public class Generators {
	public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
		for (int i = 0; i < n; i++)
			coll.add(gen.next());
		return coll;
	}

	public static void main(String[] args) {
		Collection<Coffee> coffee = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
		for (Coffee c : coffee)
			System.out.println(c);
		Collection<Integer> fnumbers = fill(new ArrayList<Integer>(), new Fibonacci(), 12);
		for (int i : fnumbers)
			System.out.print(i + ", ");
	}
}