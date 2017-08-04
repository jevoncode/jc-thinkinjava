package com.jc.concurrency.deadlock;

/**
 * 哲学家问题里的 筷子
 * @author jevoncode
 *
 */
public class Chopstick {
	private boolean taken = false;

	public synchronized void take() throws InterruptedException {
		while (taken)
			wait();
		taken = true;
	}

	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
