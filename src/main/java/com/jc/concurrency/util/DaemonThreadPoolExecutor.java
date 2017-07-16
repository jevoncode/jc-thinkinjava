package com.jc.concurrency.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DaemonThreadPoolExecutor 自己实现后台线程的线程池
 * @author jevoncode
 *
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
	}
}