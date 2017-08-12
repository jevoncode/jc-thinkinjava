package com.jc.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 不太正确的测试各种互斥的性能对比，因为多线程下测试互斥才存在意义，而且最好是复杂的任务
 * @author jevoncode
 *
 */
abstract class Incrementable {
	protected long counter = 0;

	public abstract void increment();
}

class SynchronizingTest extends Incrementable {
	public synchronized void increment() {
		++counter;
	}
}

class LockingTest extends Incrementable {
	private Lock lock = new ReentrantLock();

	public void increment() {
		lock.lock();
		try {
			++counter;
		} finally {
			lock.unlock();
		}
	}
}

public class SimpleMicroBenchmark {
	static long test(Incrementable incr) {
		long start = System.nanoTime();
		for (long i = 0; i < 10000000L; i++)
			incr.increment();
		return System.nanoTime() - start;
	}

	public static void main(String[] args) {
		long synchTime = test(new SynchronizingTest());
		long lockTime = test(new LockingTest());
		System.out.printf("synchronized: %1$10d\n", synchTime);
		System.out.printf("Lock: %1$10d\n", lockTime);
		System.out.printf("Lock/synchronized = %1$.3f", (double) lockTime / (double) synchTime);
	}
}