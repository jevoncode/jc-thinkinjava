package com.jc.containers;

/**
 * 测试  引用 的例子
 * 有finalize()方法来体现是否被回收
 * @author jevoncode
 *
 */
public class VeryBig {
	private static final int SIZE = 10000;
	private long[] la = new long[SIZE];
	private String ident;

	public VeryBig(String id) {
		ident = id;
	}

	public String toString() {
		return ident;
	}

	
	protected void finalize() {
		System.out.println("Finalizing " + ident);
	}
}
