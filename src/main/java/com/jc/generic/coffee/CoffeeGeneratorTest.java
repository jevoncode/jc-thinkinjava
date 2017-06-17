package com.jc.generic.coffee;

/**
 * 哈哈，终于找到Java编程思想书里的一个bug了，这main方法不能在CoffeeGenerator类里，不然会和泛型冲突
 * @author jevoncode
 *
 */
public class CoffeeGeneratorTest {
	public static void main(String[] args) {
		CoffeeGenerator gen = new CoffeeGenerator();
		for (int i = 0; i < 5; i++)
			System.out.println(gen.next());
		for (Coffee c : new CoffeeGenerator<Coffee>(5))
			System.out.println(c);
	}
}
