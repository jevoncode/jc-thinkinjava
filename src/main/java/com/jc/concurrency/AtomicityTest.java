package com.jc.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 除非是并发的专家，不然共享可变的值的任何操作，都得加锁synchronized
 * @author jevoncode
 *
 */
public class AtomicityTest implements Runnable {
	private int i = 0;

	public int getValue() {
		return i;
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	public void run() {
		while (true)
			evenIncrement();
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}