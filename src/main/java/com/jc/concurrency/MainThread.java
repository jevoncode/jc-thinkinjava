package com.jc.concurrency;

/**
 * main线程直接执行任务方法
 * @author jevoncode
 *
 */
public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}
}
