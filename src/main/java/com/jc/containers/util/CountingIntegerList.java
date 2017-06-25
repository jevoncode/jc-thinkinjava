package com.jc.containers.util;

import java.util.AbstractList;

/**
 * 吊炸天，还可以在集合中这样使用 享元模式，集合不用保存数据，而是get()方法的时候再去找，666
 * @author jevoncode
 *
 */
public class CountingIntegerList extends AbstractList<Integer> {
	private int size;

	public CountingIntegerList(int size) {
		this.size = size < 0 ? 0 : size;
	}

	public Integer get(int index) {
		return Integer.valueOf(index);
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		System.out.println(new CountingIntegerList(30));
	}
}