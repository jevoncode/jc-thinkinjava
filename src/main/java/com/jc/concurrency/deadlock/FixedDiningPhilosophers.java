package com.jc.concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 从最简单的第4个条件入手，打破循环等待，即可解决死锁问题
 * 
 * 最后一个哲学家不是先拿左边筷子，而是先拿右边筷子
 * @author jevoncode
 *
 */
public class FixedDiningPhilosophers {
	public static void main(String[] args) throws Exception {
		int ponder = 5;
		if (args.length > 0)
			ponder = Integer.parseInt(args[0]);
		int size = 5;
		if (args.length > 1)
			size = Integer.parseInt(args[1]);
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++)
			sticks[i] = new Chopstick();
		for (int i = 0; i < size; i++)
			if (i < (size - 1))
				exec.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
			else
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
		if (args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else {
			System.out.println("Press ‘Enter’ to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}