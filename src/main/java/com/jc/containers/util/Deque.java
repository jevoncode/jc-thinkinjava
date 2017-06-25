package com.jc.containers.util;

import java.util.LinkedList;
/**
 * 在Java6之前是没有显式的双向队列，于是这里定义一个Deque
 * @author jevoncode
 *
 */
public class Deque<T> {
	private LinkedList<T> deque = new LinkedList<T>();

	public void addFirst(T e) {
		deque.addFirst(e);
	}

	public void addLast(T e) {
		deque.addLast(e);
	}

	public T getFirst() {
		return deque.getFirst();
	}

	public T getLast() {
		return deque.getLast();
	}

	public T removeFirst() {
		return deque.removeFirst();
	}

	public T removeLast() {
		return deque.removeLast();
	}

	public int size() {
		return deque.size();
	}

	public String toString() {
		return deque.toString();
	}
}
