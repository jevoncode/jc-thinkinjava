package com.jc.generic;

/**
 *
 * 这个例子和 ArrayOfGenericReference ，这两个例子有点混淆人，实际是想说明数组的类型有限制，跟泛型关系不大。
 * 做了个论据在展示GenericArray和GenericArray2 为什么会运行时才报错。
 * 论据是数组在定义类型后，只能是类型及其子类才能放入数组中，而泛型可能会触犯到这个限制
 * @author jevoncode
 */
public class ArrayOfGeneric {

	static final int SIZE = 100;
	static Generic<Integer>[] gia;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// 编译通过，但运行时会出错ClassCastException: [Ljava.lang.Object; cannot be cast to [Lcom.jc.generic.Generic;
		// ! gia = (Generic<Integer>[])new Object[SIZE];
		
		// Runtime type is the raw (erased) type:
		gia = (Generic<Integer>[]) new Generic[SIZE];
		System.out.println(gia.getClass().getSimpleName());
		
		gia[0] = new Generic<Integer>();
		// ! gia[1] = new Object(); // Compile-time error
		// Discovers type mismatch at compile time:
		// ! gia[2] = new Generic<Double>();
	}
}
