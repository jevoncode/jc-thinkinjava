package com.jc.generic.util;

class Amphibian {
}

class Vehicle {
}

/**
 * 使用元组的例子，会发现实例化一个元组很繁琐
 * @author jevoncode
 *
 */
public class TupleTest {
	static TwoTuple<String, Integer> f() {
		// Autoboxing converts the int to Integer:
		return new TwoTuple<String, Integer>("hi", 47);
	}

	static ThreeTuple<Amphibian, String, Integer> g() {
		return new ThreeTuple<Amphibian, String, Integer>(new Amphibian(), "hi", 47);
	}

	static FourTuple<Vehicle, Amphibian, String, Integer> h() {
		return new FourTuple<Vehicle, Amphibian, String, Integer>(new Vehicle(), new Amphibian(), "hi", 47);
	}

	public static void main(String[] args) {
		TwoTuple<String, Integer> ttsi = f();
		System.out.println(ttsi);
		// ttsi.first = "there"; // Compile error: final
		System.out.println(g());
		System.out.println(h());
	}
}