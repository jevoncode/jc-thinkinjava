package com.jc.concurrency;

/**
 * 多线程例子，但是单线程创建多线程，所以LiftOff的id不会重复
 * @author jevoncode
 *
 */
public class MoreBasicThreads {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new Thread(new LiftOff()).start();
		System.out.println("Waiting for LiftOff");
	}
}
