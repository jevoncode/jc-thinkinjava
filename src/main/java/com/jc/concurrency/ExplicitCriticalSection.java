package com.jc.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用显示锁 重入锁ReentrantLock 来创建关键区（Critical Section）
 * 
 * 哈哈，作者不细心了，这里有bug。 原因是因为getPair()锁的是PairManager，用ReentrantLock并不会阻碍线程去获取PairManager类的synchronized锁
 * @author jevoncode
 *
 */
//Synchronize the entire method:
class ExplicitPairManager1 extends PairManager {
	private Lock lock = new ReentrantLock();

	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
}

// Use a critical section:
class ExplicitPairManager2 extends PairManager {
	private Lock lock = new ReentrantLock();

	
	/**需复写getPair方法，不如会导致异常出现，出现线程不安全。因为increment()和getPair()必须同一个锁  这段代码不是书中的代码**/
	public Pair getPair() {
		 
		lock.lock();
		try {
			return new Pair(p.getX(), p.getY());
		} finally {
			lock.unlock();
		}
	}
	/**需复写getPair方法，不如会导致异常出现，出现线程不安全。因为increment()和getPair()必须同一个锁 这段代码不是书中的代码**/
	
	public void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
}

public class ExplicitCriticalSection {
	public static void main(String[] args) throws Exception {
		PairManager pman1 = new ExplicitPairManager1(), pman2 = new ExplicitPairManager2();
		System.out.println(pman1);
		System.out.println(pman2);
		CriticalSection.testApproaches(pman1, pman2);
	}
}