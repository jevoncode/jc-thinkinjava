package com.jc.generic;

import java.util.Iterator;

/**
 * 这里跟CoffeeGenerator不一样，除了接口，其他没用泛型，所以main方法有Integer也可以编译通过
 * @author jevoncode
 *
 */
public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
	private int n;

	public IterableFibonacci(int count) {
		n = count;
	}

	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			public boolean hasNext() {
				return n > 0;
			}

			public Integer next() {
				n--;
				return IterableFibonacci.this.next();
			}

			public void remove() { // Not implemented
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		for (Integer i : new IterableFibonacci(18))
			System.out.print(i + " ");
	}
}