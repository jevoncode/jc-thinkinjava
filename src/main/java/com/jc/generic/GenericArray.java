package com.jc.generic;

/**
 * GenericArray和GenericArray2两个例子都展示了，触犯了ArrayOfGeneric所定下来论据
 * @author jevoncode
 */
public class GenericArray<T> {
	private T[] array;

	@SuppressWarnings("unchecked")
	public GenericArray(int sz) {
		array = (T[]) new Object[sz];
	}

	public void put(int index, T item) {
		array[index] = item;
	}

	public T get(int index) {
		return array[index];
	}

	// Method that exposes the underlying representation:
	public T[] rep() {
		return array;
	}

	public static void main(String[] args) {
		GenericArray<Integer> gai = new GenericArray<Integer>(10);
		// 编译通过，但运行时会出错ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
		// ! Integer[] ia = gai.rep();
		// This is OK:
		Object[] oa = gai.rep();
	}
} 
