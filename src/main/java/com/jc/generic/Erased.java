package com.jc.generic;

/**
 * java泛型的一些限制，所有跟运行时类型相关的操作都不能用 
 * @author jevoncode
 */
//public class Erased<T> {
//	private final int SIZE = 100;
//
//	public static void f(Object arg) {
//		if (arg instanceof T) {
//		} // Error
//		T var = new T(); // Error
//		T[] array = new T[SIZE]; // Error
//		T[] array = (T) new Object[SIZE]; // Unchecked warning
//	}
//}