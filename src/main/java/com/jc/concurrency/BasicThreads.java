package com.jc.concurrency;

/**
 * 单线程例子
 * @author jevoncode
 *
 */
public class BasicThreads {
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("Waiting for LiftOff");
	}
}
