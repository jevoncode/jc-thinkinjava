package com.jc.concurrency;

/**
 * 添加关键字synchronized做同步操作，所以不会产生偶数的情况
 * 
 * so  该程序运行是不会停止的
 * @author jevoncode
 *
 */
public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	public synchronized int next() {
		++currentEvenValue;
		Thread.yield(); // Cause failure faster
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}