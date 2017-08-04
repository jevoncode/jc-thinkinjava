package com.jc.concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 满足4个条件的死锁例子 
 * 
 * 1.互斥条件
 * 2.至少有一个任务必须持有一个资源且正在等待取一个当前被别的任务持有的资源
 * 3.被占有的资源不能被任务抢过去
 * 4.必须有循环等待
 * @author jevoncode
 *
 */
public class DeadlockingDiningPhilosophers {
	public static void main(String[] args) throws Exception {
		int ponder = 1;
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
			exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponder));
		if (args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else {
			System.out.println("Press ‘Enter’ to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
