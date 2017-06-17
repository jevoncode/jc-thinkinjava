package com.jc.generic.util.tuple;
/**
 * 一个新的概念"元组(tuple)"，方便方法返回多个值，通用且又不失编译器的保障，可以用于service层返回给controller层的结果
 * @author jevoncode
 *
 */
public class TwoTuple<A, B> {
	public final A first;
	public final B second;

	public TwoTuple(A a, B b) {
		first = a;
		second = b;
	}

	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}
