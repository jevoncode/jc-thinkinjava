package com.jc.generic;
/*
 *  由于擦除，编译器无法将obj.f()方法和HasF类关联起来
class Manipulator<T> {
	private T obj;

	public Manipulator(T x) {
		obj = x;
	}

	// Error: cannot find symbol: method f():
	public void manipulate() {
		obj.f();
	}
}

public class Manipulation {
	public static void main(String[] args) {
		HasF hf = new HasF();
		Manipulator<HasF> manipulator = new Manipulator<HasF>(hf);
		manipulator.manipulate();
	}
}*/