package com.jc.generic;

import com.jc.generic.util.Generator;

/**
 * 哟，实现泛型的接口，自己还可以不用指定泛型类型
 * @author jevoncode
 *
 */
public class Fibonacci implements Generator<Integer> {
	private int count = 0;

	public Integer next() {
		return fib(count++);
	}

	private int fib(int n) {
		if (n < 2)
			return 1;
		return fib(n - 2) + fib(n - 1);
	}

	public static void main(String[] args) {
		Fibonacci gen = new Fibonacci();
		for (int i = 0; i < 18; i++)
			System.out.print(gen.next() + " ");
	}
}
