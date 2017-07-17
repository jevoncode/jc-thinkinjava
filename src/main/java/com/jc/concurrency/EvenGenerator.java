package com.jc.concurrency;
/**
 * 共享资源生成器实现，生成int
 * 
 * 这种未做同步操作的去读取通过资源，将导致竞争条件race condition
 * @author jevoncode
 *
 */
public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	public int next() {
		++currentEvenValue; // Danger point here!
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}