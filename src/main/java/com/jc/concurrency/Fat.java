package com.jc.concurrency;

/**
 * 池对象操作，使用Semaphore来控制checkin和checkout
 * @author jevoncode
 * 
 */
public class Fat {
	private volatile double d; // Prevent optimization
	private static int counter = 0;
	private final int id = counter++;

	public Fat() {
		// Expensive, interruptible operation:
		for (int i = 1; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}

	public void operation() {
		System.out.println(this);
	}

	public String toString() {
		return "Fat id: " + id;
	}
}