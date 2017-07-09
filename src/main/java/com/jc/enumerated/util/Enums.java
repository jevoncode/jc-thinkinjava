package com.jc.enumerated.util;

import java.util.Random;

/**
 * 随机获取枚举实例的工具类
 * 
 * 泛型<T extends Enum<T>>  表明T 是枚举实例
 * @author jevoncode
 *
 */
public class Enums {
	private static Random rand = new Random(47);

	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}