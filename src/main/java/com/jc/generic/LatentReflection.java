package com.jc.generic;

import java.lang.reflect.Method;

/**
 * 在不实现接口的情况下，满足潜在机制的实现，不过又带来个问题，失去了编译期的保障
 * @author jevoncode
 *
 */
//Does not implement Performs:
class Mime {
	public void walkAgainstTheWind() {
	}

	public void sit() {
		System.out.println("Pretending to sit");
	}

	public void pushInvisibleWalls() {
	}

	public String toString() {
		return "Mime";
	}
}

// Does not implement Performs:
class SmartDog {
	public void speak() {
		System.out.println("Woof!");
	}

	public void sit() {
		System.out.println("Sitting");
	}

	public void reproduce() {
	}
}

class CommunicateReflectively {
	public static void perform(Object speaker) {
		Class<?> spkr = speaker.getClass();
		try {
			try {
				Method speak = spkr.getMethod("speak");
				speak.invoke(speaker);
			} catch (NoSuchMethodException e) {
				System.out.println(speaker + " cannot speak");
			}
			try {
				Method sit = spkr.getMethod("sit");
				sit.invoke(speaker);
			} catch (NoSuchMethodException e) {
				System.out.println(speaker + " cannot sit");
			}
		} catch (Exception e) {
			throw new RuntimeException(speaker.toString(), e);
		}
	}
}

public class LatentReflection {
	public static void main(String[] args) {
		CommunicateReflectively.perform(new SmartDog());
		CommunicateReflectively.perform(new Robot());
		CommunicateReflectively.perform(new Mime());
	}
}