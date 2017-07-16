package com.jc.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.jc.concurrency.util.DaemonThreadFactory;

/**
 * 实现ThreadFactory来创建线程池，里面的线程都是后台线程
 * 
 * @author jevoncode
 *
 */
public class DaemonFromFactory implements Runnable {
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++)
			exec.execute(new DaemonFromFactory());
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500); // Run for a while
	}
}