package com.jc.generic;

class Automobile {
}

/**
 * 首例，展示不通用的代码，只能针对业务一个一个写这样的类
 * @author jevoncode
 *
 */
public class Holder1 {
	private Automobile a;

	public Holder1(Automobile a) {
		this.a = a;
	}

	Automobile get() {
		return a;
	}
} 
