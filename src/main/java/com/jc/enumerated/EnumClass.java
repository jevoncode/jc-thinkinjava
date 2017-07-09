package com.jc.enumerated;

enum Shrubbery {
	GROUND, CRAWLING, HANGING
}

/**
 * Enum的定义，还可以根据字符串来实例化枚举对象
 * @author jevoncode
 *
 */
public class EnumClass {
	public static void main(String[] args) {
		for (Shrubbery s : Shrubbery.values()) {
			System.out.println(s + " ordinal: " + s.ordinal());
			System.out.print(s.compareTo(Shrubbery.CRAWLING) + " ");
			System.out.print(s.equals(Shrubbery.CRAWLING) + " ");
			System.out.println(s == Shrubbery.CRAWLING);
			System.out.println(s.getDeclaringClass());
			System.out.println(s.name());
			System.out.println("----------------------");
		}
		// Produce an enum value from a string name:
		for (String s : "HANGING CRAWLING GROUND".split(" ")) {
			Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
			System.out.println(shrub);
		}
	}
}
