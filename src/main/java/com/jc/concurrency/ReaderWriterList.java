package com.jc.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁ReentrantReadWriteLock，读锁和写锁分开
 * 读锁可以多个任务获取，但只要有个写锁，则不可以使用读锁，类似于沈询说的读写概念
 * @author jevoncode
 *
 */
public class ReaderWriterList<T> {
	private ArrayList<T> lockedList;
	// Make the ordering fair:
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	public ReaderWriterList(int size, T initialValue) {
		lockedList = new ArrayList<T>(Collections.nCopies(size, initialValue));
	}

	public T set(int index, T element) {
		Lock wlock = lock.writeLock();
		wlock.lock();
		try {
			return lockedList.set(index, element);
		} finally {
			wlock.unlock();
		}
	}

	public T get(int index) {
		Lock rlock = lock.readLock();
		rlock.lock();
		try {
			// Show that multiple readers
			// may acquire the read lock:
			if (lock.getReadLockCount() > 1)
				System.out.println(lock.getReadLockCount());
			return lockedList.get(index);
		} finally {
			rlock.unlock();
		}
	}

	public static void main(String[] args) throws Exception {
		new ReaderWriterListTest(30, 1);
	}
}

class ReaderWriterListTest {
	ExecutorService exec = Executors.newCachedThreadPool();
	private final static int SIZE = 100;
	private static Random rand = new Random(47);
	private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE, 0);

	private class Writer implements Runnable {
		public void run() {
			try {
				for (int i = 0; i < 20; i++) { // 2 second test
					list.set(i, rand.nextInt());
					TimeUnit.MILLISECONDS.sleep(100);
				}
			} catch (InterruptedException e) {
				// Acceptable way to exit
			}
			System.out.println("Writer finished, shutting down");
			exec.shutdownNow();
		}
	}

	private class Reader implements Runnable {
		public void run() {
			try {
				while (!Thread.interrupted()) {
					for (int i = 0; i < SIZE; i++) {
						list.get(i);
						TimeUnit.MILLISECONDS.sleep(1);
					}
				}
			} catch (InterruptedException e) {
				// Acceptable way to exit
			}
		}
	}

	public ReaderWriterListTest(int readers, int writers) {
		for (int i = 0; i < readers; i++)
			exec.execute(new Reader());
		for (int i = 0; i < writers; i++)
			exec.execute(new Writer());
	}
}
