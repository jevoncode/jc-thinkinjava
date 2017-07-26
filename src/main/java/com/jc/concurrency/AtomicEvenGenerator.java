package com.jc.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 使用原子类修改MutexEvenGenerator
 * @author jevoncode
 *
 */
public class AtomicEvenGenerator extends IntGenerator {
	private AtomicInteger currentEvenValue = new AtomicInteger(0);

	public int next() {
		return currentEvenValue.addAndGet(2);
	}

	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
