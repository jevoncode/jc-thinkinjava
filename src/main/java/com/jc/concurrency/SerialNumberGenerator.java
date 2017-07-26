package com.jc.concurrency;

/**
 * 虽然看起来就一条语句，累加serialNumber，在C++这些更底层的语言中，是可以做到原子性
 * 但在Java就不行，从反编译出来的Atomicity就知道，读和写是分开的，中间过程中其他线程就可以做很多东西
 * @author jevoncode
 *
 */
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++; // Not thread-safe
	}
}
