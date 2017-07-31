package com.jc.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SleepBlocked例子展示了synchronized的锁是不可以中断，这是很危险的。
 * 所以ReentrantLock提供了可中断的能力
 * @author jevoncode
 *
 */
class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		// Acquire it right away, to demonstrate interruption
		// of a task blocked on a ReentrantLock:
		lock.lock();
	}

	public void f() {
		try {
			// This will never be available to a second task
			lock.lockInterruptibly(); // Special call  //只有这点才可以被中断，这点是被阻塞的地方
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}

public class Interrupting2 {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(10);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}
}