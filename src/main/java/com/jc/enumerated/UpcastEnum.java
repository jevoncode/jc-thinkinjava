package com.jc.enumerated;

/**
 * 不通过values()方法遍历枚举实例，class的getEnumConstants()也可以
 * @author jevoncode
 *
 */
enum Search {
	HITHER, YON
}

public class UpcastEnum {
	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum e = Search.HITHER; // Upcast
		// e.values(); // No values() in Enum
		for (Enum en : e.getClass().getEnumConstants())
			System.out.println(en);
	}
}