package com.jc.generic;


/**
 * ClassAsFactory这个例子引用工厂类不能完全解决问题，因为存在没有默认构造方法的类，这样就需要单独为每个类创建一个工厂类
 * @author jevoncode
 */
interface FactoryI<T> {
	T create();
}

class Foo2<T> {
	private T x;

	public <F extends FactoryI<T>> Foo2(F factory) {
		x = factory.create();
	}
}

class IntegerFactory implements FactoryI<Integer> {
	public Integer create() {
		return new Integer(0);
	}
}

class Widget {
	public static class Factory implements FactoryI<Widget> {
		public Widget create() {
			return new Widget();
		}
	}
}

public class FactoryConstraint {
	public static void main(String[] args) {
		new Foo2<Integer>(new IntegerFactory());
		new Foo2<Widget>(new Widget.Factory());
	}
}
