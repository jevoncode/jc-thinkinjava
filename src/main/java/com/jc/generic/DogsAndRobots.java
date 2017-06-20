package com.jc.generic;

/**
 * 潜在类型机制（鸭子类型机制）是其他语言有的机制，而在java中也可以，只不过要增加限制，必须实现某个接口
 * @author jevoncode
 *
 */
class PerformingDog extends Dog implements Performs {
	public void speak() {
		System.out.println("Woof!");
	}

	public void sit() {
		System.out.println("Sitting");
	}

	public void reproduce() {
	}
}

class Robot implements Performs {
	public void speak() {
		System.out.println("Click!");
	}

	public void sit() {
		System.out.println("Clank!");
	}

	public void oilChange() {
	}
}

class Communicate {
	public static <T extends Performs> void perform(T performer) {
		performer.speak();
		performer.sit();
	}
}

public class DogsAndRobots {
	public static void main(String[] args) {
		PerformingDog d = new PerformingDog();
		Robot r = new Robot();
		Communicate.perform(d);
		Communicate.perform(r);
	}
}