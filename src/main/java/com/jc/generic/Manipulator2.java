package com.jc.generic;

/**
 * 为了解决上一个例子Manipulator类不知道泛型里的方法，于是泛型增加边界 extends
 * 这样编译器就知道原来泛型obj有f()方法
 * @author jevoncode
 *
 */
public class Manipulator2<T extends HasF> {
	private T obj;

	public Manipulator2(T x) {
		obj = x;
	}

	public void manipulate() {
		obj.f();
	}
}