package com.jc.generic;

/**
 * 单线程情况下可记录对象数量的类，用于示例BasicGenerator使用的
 * @author jevoncode
 *
 */
public class CountedObject {
	private static long counter = 0;
	private final long id = counter++;

	public long id() {
		return id;
	}

	public String toString() {
		return "CountedObject " + id;
	}
}