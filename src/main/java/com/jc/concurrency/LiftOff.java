package com.jc.concurrency;

/**
 * 任务类
 * @author jevoncode
 *
 */
public class LiftOff implements Runnable {
	protected int countDown = 10; // Default
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
	}

	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();  //告诉Java线程机制中的线程调度器，这是最好的切换给其他任务一段时间的大好时机，Java是抢占式（preemptive）线程
		}
	}
}