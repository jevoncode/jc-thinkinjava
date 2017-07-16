package com.jc.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 由于后台程序在所有非后台程序结束后就会结束，所以不能保证finally的执行
 * @author jevoncode
 *
 */
class ADaemon implements Runnable {
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}
}