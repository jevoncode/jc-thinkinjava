package com.jc.generic;

/**
 * 一个有趣的现象，数组引用是父类是，实际数组类型是子类，既然在编译期父类的实例可以赋值给这数组引用，但运行时却报错
 * 在运行时报错，是一个非常非常bad的设计 
 * @author jevoncode
 */
class Fruit {
}

class Apple extends Fruit {
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}

public class CovariantArrays {
	public static void main(String[] args) {
		Fruit[] fruit = new Apple[10];
		fruit[0] = new Apple(); // OK
		fruit[1] = new Jonathan(); // OK
		// Runtime type is Apple[], not Fruit[] or Orange[]:
		try {
			// Compiler allows you to add Fruit:
			fruit[0] = new Fruit(); // ArrayStoreException
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			// Compiler allows you to add Oranges:
			fruit[0] = new Orange(); // ArrayStoreException
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}