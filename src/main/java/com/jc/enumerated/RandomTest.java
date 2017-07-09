package com.jc.enumerated;

import com.jc.enumerated.util.Enums;

/**
 * 随机获取枚举实例的工具类例子
 * 
 * @author jevoncode
 *
 */
enum Activity {
	SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING
}

public class RandomTest {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++)
			System.out.print(Enums.random(Activity.class) + " ");
	}
}