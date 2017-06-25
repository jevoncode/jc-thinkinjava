package com.jc.containers.util;

import java.util.ArrayList;

import com.jc.generic.util.Generator;

/**
 * Collection对象都是接受另外个Collection对象的作为构造方法的参数
 * 但没有现成的工具可以根据生成器去生成想要的集合，所以才写出这个例子CollectionData
 * @author jevoncode
 *
 */
public class CollectionData<T> extends ArrayList<T> {
	public CollectionData(Generator<T> gen, int quantity) {
		for (int i = 0; i < quantity; i++)
			add(gen.next());
	}

	// A generic convenience method:
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
		return new CollectionData<T>(gen, quantity);
	}
}