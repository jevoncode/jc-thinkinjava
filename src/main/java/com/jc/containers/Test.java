package com.jc.containers;

/**
 * 防止代码重复和提供测试得一致性，所以建立个测试框架
 * Test<C>是测试操作接口
 * @author jevoncode
 *
 * @param <C>
 */
public abstract class Test<C> {
	String name;

	public Test(String name) {
		this.name = name;
	}

	// Override this method for different tests.
	// Returns actual number of repetitions of test.
	abstract int test(C container, TestParam tp);
}