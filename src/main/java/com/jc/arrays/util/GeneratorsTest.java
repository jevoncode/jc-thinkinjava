package com.jc.arrays.util;

import com.jc.generic.util.Generator;

/**
 * 数据生成器测试，通配符还可以这样用，嵌套类还可以通过Class.getClasses()获取
 * @author jevoncode
 *
 */
public class GeneratorsTest {
	public static int size = 10;

	public static void test(Class<?> surroundingClass) {
		for (Class<?> type : surroundingClass.getClasses()) {
			System.out.print(type.getSimpleName() + ": ");
			try {
				Generator<?> g = (Generator<?>) type.newInstance();
				for (int i = 0; i < size; i++)
					System.out.printf(g.next() + " ");
				System.out.println();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		test(CountingGenerator.class);
	}
}