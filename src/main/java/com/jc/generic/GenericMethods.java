package com.jc.generic;

/**
 * 泛型方法 需在方法前有<T>表明该方法是泛型方法
 * @author jevoncode
 *
 */
public class GenericMethods {
	public <T> void f(T x) {
		System.out.println(x.getClass().getName());
	}

	public static void main(String[] args) {
		GenericMethods gm = new GenericMethods();
		gm.f("");
		gm.f(1);
		gm.f(1.0);
		gm.f(1.0F);
		gm.f('c');
		gm.f(gm);
	}
}
