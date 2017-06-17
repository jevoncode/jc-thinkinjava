package com.jc.generic.util.tuple;

/**
 * 使用泛型的继承，从而创建更长的元组
 * @author jevoncode
 *
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

	public final C third;

	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		third = c;
	}

	public String toString() {
		return "(" + first + ", " + second + ", " + third + ")";
	}

}
