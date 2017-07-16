package com.jc.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * 实现ThreadFactory来创建线程池，里面的线程都是后台线程
 * @author jevoncode
 *
 */
public class DaemonThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
}