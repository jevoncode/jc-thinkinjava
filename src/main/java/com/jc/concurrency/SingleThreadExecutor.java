package com.jc.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程的线程池，对序列化类型的需求等有很大帮助
 * @author jevoncode
 *
 */
public class SingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++)
			exec.execute(new LiftOff());
		exec.shutdown();
	}
}
