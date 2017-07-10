package com.jc.designpattern.enumerated;

import java.util.Random;
/**
 * 用枚举实现状态机设计模式，好像就是用来做测试，测试任意状态的转换，看有没bug
 * 
 * Input是基础枚举，在VendingMachine将再次被分组
 * @author jevoncode
 *
 */
public enum Input {
	NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100), TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50), ABORT_TRANSACTION {

		public int amount() { // Disallow
			throw new RuntimeException("ABORT.amount()");
		}

	},
	STOP { // This must be the last instance.

		public int amount() { // Disallow
			throw new RuntimeException("SHUT_DOWN.amount()");
		}
	};

	int value; // In cents

	Input(int value) {
		this.value = value;
	}

	Input() {
	}

	int amount() {
		return value;
	}; // In cents

	static Random rand = new Random(47);

	public static Input randomSelection() {
		// Don’t include STOP:
		return values()[rand.nextInt(values().length - 1)];
	}
}
