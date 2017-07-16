package com.jc.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 优先级例子，需配合CPU开销大的运算才能展示出优先级，线程调度器才来得及接入去交换任务
 * 
 * Java线程级别最好只使用Thread.MIN_PRIORITY、Thread.NORM_PRIORITY和Thread.MAX_PRIORITY
 * @author jevoncode
 *
 */
public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d; // No optimization
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}

	public void run() {
		Thread.currentThread().setPriority(priority);
		while (true) {
			// An expensive, interruptable operation:
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		exec.execute(new SimplePriorities(Thread.NORM_PRIORITY));
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
