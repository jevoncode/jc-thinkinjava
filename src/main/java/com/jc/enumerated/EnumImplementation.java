package com.jc.enumerated;

import java.util.Random;

import com.jc.generic.util.Generator;
/**
 * 枚举不可以被继承，自己也不可以继承别人，因为继承了enum，但可以实现别的接口
 * @author jevoncode
 *
 */
enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	private Random rand = new Random(47);

	public CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
}

public class EnumImplementation {
	public static <T> void printNext(Generator<T> rg) {
		System.out.print(rg.next() + ", ");
	}

	public static void main(String[] args) {
		// Choose any instance:
		CartoonCharacter cc = CartoonCharacter.BOB;
		for (int i = 0; i < 10; i++)
			printNext(cc);
	}
}