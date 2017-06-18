package com.jc.generic.util.tuple;

import java.util.ArrayList;
/**
 * 使用泛型可以构建更复杂的模型，例子1
 * @author jevoncode
 *
 */
public class TupleList<A, B, C, D> extends ArrayList<FourTuple<A, B, C, D>> {
	public static void main(String[] args) {
		TupleList<Vehicle, Amphibian, String, Integer> tl = new TupleList<Vehicle, Amphibian, String, Integer>();
		tl.add(TupleTest.h());
		tl.add(TupleTest.h());
		for (FourTuple<Vehicle, Amphibian, String, Integer> i : tl)
			System.out.println(i);
	}
}
