package com.jc.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 使用Thread.UncaughtExceptionHandler处理线程抛出的异常
 * 
 * MyUncaughtExceptionHandler会新建线程去处理其他线程跑出来的异常
 * 
 * @author jevoncode
 *
 */
class ExceptionThread2 implements Runnable {
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by " + t);
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + t + "'s " + e);
	}
}

class HandlerThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " creating new Thread");
		Thread t = new Thread(r);
		System.out.println("created " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}
}

public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}
}/*
	 * output:
	 * 
	 * com.jc.concurrency.HandlerThreadFactory@4e25154f creating new Thread
	 * created Thread[Thread-0,5,main] eh =
	 * com.jc.concurrency.MyUncaughtExceptionHandler@70dea4e run() by
	 * Thread[Thread-0,5,main] eh =
	 * com.jc.concurrency.MyUncaughtExceptionHandler@70dea4e
	 * com.jc.concurrency.HandlerThreadFactory@4e25154f creating new Thread
	 * created Thread[Thread-1,5,main] eh =
	 * com.jc.concurrency.MyUncaughtExceptionHandler@5490c2f5 caught
	 * Thread[Thread-0,5,main]'s java.lang.RuntimeException
	 * 
	 * 
	 * 
	 */