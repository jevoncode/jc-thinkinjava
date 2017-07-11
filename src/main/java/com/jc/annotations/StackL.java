package com.jc.annotations;

import java.util.LinkedList;

/**
 * 泛型的测试
 * @author jevoncode
 *
 * @param <T>
 */
public class StackL<T> {
	private LinkedList<T> list = new LinkedList<T>();

	public void push(T v) {
		list.addFirst(v);
	}

	public T top() {
		return list.getFirst();
	}

	public T pop() {
		return list.removeFirst();
	}
}