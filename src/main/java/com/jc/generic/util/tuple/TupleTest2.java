package com.jc.generic.util.tuple;

import static com.jc.generic.util.tuple.Tuple.*;

/**
 * 根据泛型方法的优势，就是可以减少代码量就可以实例化泛型对象，所以重写一次 TupleTest
 * 
 * @author jevoncode
 *
 */
public class TupleTest2 {
	static TwoTuple<String, Integer> f() {
		return tuple("hi", 47);
	}

	static TwoTuple f2() {
		return tuple("hi", 47);
	}

	static ThreeTuple<Amphibian, String, Integer> g() {
		return tuple(new Amphibian(), "hi", 47);
	}

	static FourTuple<Vehicle, Amphibian, String, Integer> h() {
		return tuple(new Vehicle(), new Amphibian(), "hi", 47);
	}

	public static void main(String[] args) {
		TwoTuple<String, Integer> ttsi = f();
		System.out.println(ttsi);
		System.out.println(f2());
		System.out.println(g());
		System.out.println(h());
	}
}