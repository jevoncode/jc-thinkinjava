package com.jc.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * 
 * GenericArray和GenericArray2两个例子都展示了，触犯了ArrayOfGeneric所定下来论据
 * 所以在这个类里，不再是使用Object做数组，而是使用类型标签(tag type)去初始化数组
 * 也再次证明泛型数组实例化只能通过 Array.newInstance，不然就很容易触犯数组的限制
 * 
 * 题外话: 表明泛型不好定义，分分钟出触犯原有的规则都不知道，就好像原本数组用得好好的，一加上泛型，就危险了，忘记原来Object不可以转为具体类型
 * @author jevoncode
 */
public class GenericArrayWithTypeToken<T> {
	private T[] array;

	@SuppressWarnings("unchecked")
	public GenericArrayWithTypeToken(Class<T> type, int sz) {
		array = (T[]) Array.newInstance(type, sz);
	}

	public void put(int index, T item) {
		array[index] = item;
	}

	public T get(int index) {
		return array[index];
	}

	// Expose the underlying representation:
	public T[] rep() {
		return array;
	}

	public static void main(String[] args) {
		GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<Integer>(Integer.class, 10);
		// This now works:
		Integer[] ia = gai.rep();
	}
}
