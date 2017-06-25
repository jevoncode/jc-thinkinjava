package com.jc.arrays.util;

import com.jc.containers.util.CollectionData;
import com.jc.generic.util.Generator;

/**
 * 数组生成工具，注意：包装类的数组是不能直接赋值给基本类型数组，需用工具类ConvertTo
 * 使用容器的工具类来生成数组
 * @author jevoncode
 *
 */
public class Generated {
	// Fill an existing array:
	public static <T> T[] array(T[] a, Generator<T> gen) {
		return new CollectionData<T>(gen, a.length).toArray(a);
	}

	// Create a new array:
	@SuppressWarnings("unchecked")
	public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
		T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
		return new CollectionData<T>(gen, size).toArray(a);
	}
}