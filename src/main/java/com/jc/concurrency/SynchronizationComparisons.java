package com.jc.concurrency;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用随机数或复杂任务，让编译器无法进行优化
 * 额，果然作者不太擅长并发，也足以证明并发的复杂性，例子还是很多问题。所以我进行修改。
 * @author jevoncode
 *
 */
abstract class Accumulator {
	public static long cycles = 50000L;
	// Number of Modifiers and Readers during each test:
	private static final int N = 4;
	public static ExecutorService exec = Executors.newFixedThreadPool(N * 2);
	private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
	protected volatile int index = 0;
	protected volatile long value = 0;
	protected long duration = 0;
	protected String id = "error";
	protected final static int SIZE = 100000;
	protected static int[] preLoaded = new int[SIZE];
	static {
		// Load the array of random numbers:
		Random rand = new Random(47);
		for (int i = 0; i < SIZE; i++)
			preLoaded[i] = rand.nextInt();
	}

	public abstract void accumulate();

	public abstract long read();

	private class Modifier implements Runnable {
		public void run() {
			for (long i = 0; i < cycles; i++)
				accumulate();
			try {
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private class Reader implements Runnable {
		private volatile long value;

		public void run() {
			for (long i = 0; i < cycles; i++)
				value = read();
			try {
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void timedTest() {
		long start = System.nanoTime();
		for (int i = 0; i < N; i++) {
			exec.execute(new Modifier());
			exec.execute(new Reader());
		}
		try {
			barrier.await();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		duration = System.nanoTime() - start;
		System.out.printf("%-13s: %13d\n", id, duration);
	}

	public static void report(Accumulator acc1, Accumulator acc2) {
		System.out.printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id, (double) acc1.duration / (double) acc2.duration);
	}
}

class BaseLine extends Accumulator {
	{
		id = "BaseLine";
	}

	public void accumulate() {
//		value += preLoaded[index++]; //是不是傻，这里没做同步，导致index异常
//		if (index >= SIZE)
//			index = 0;
		value += preLoaded[new Random().nextInt(SIZE)];
	}

	public long read() {
		return value;
	}
}

class SynchronizedTest extends Accumulator {
	{
		id = "synchronized";
	}

	public synchronized void accumulate() {
//		value += preLoaded[index++];  //虽然这里同步，不会造成index线程安全问题，但还是为了保持一直所以一起改了
//		if (index >= SIZE)
//			index = 0;
		value += preLoaded[new Random().nextInt(SIZE)];
	}

	public synchronized long read() {
		return value;
	}
}

class LockTest extends Accumulator {
	{
		id = "Lock";
	}
	private Lock lock = new ReentrantLock();

	public void accumulate() {
		lock.lock();
		try {
//			value += preLoaded[index++]; //是不是傻，这里没做同步，导致index异常
//			if (index >= SIZE)
//				index = 0;
			value += preLoaded[new Random().nextInt(SIZE)];
		} finally {
			lock.unlock();
		}
	}

	public long read() {
		lock.lock();
		try {
			return value;
		} finally {
			lock.unlock();
		}
	}
}

class AtomicTest extends Accumulator {
	{
		id = "Atomic";
	}
	private AtomicInteger index = new AtomicInteger(0);
	private AtomicLong value = new AtomicLong(0);

	public void accumulate() {
		// Oops! Relying on more than one Atomic at
		// a time doesn’t work. But it still gives us
		// a performance indicator:
//		int i = index.getAndIncrement();
//		value.getAndAdd(preLoaded[i]);
//		if (++i >= SIZE)
//			index.set(0);
		value.getAndAdd(preLoaded[new Random().nextInt(SIZE)]);
	}

	public long read() {
		return value.get();
	}
}

public class SynchronizationComparisons {
	static BaseLine baseLine = new BaseLine();
	static SynchronizedTest synch = new SynchronizedTest();
	static LockTest lock = new LockTest();
	static AtomicTest atomic = new AtomicTest();

	static void test() {
		System.out.println("============================");
		System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
		baseLine.timedTest();
		synch.timedTest();
		lock.timedTest();
		atomic.timedTest();//Cycles过10,000时AtomicLong会报数组越界异常，暂时查不出什么原因，我们先看synchronized和lock的对比
		Accumulator.report(synch, baseLine);
		Accumulator.report(lock, baseLine);
		Accumulator.report(atomic, baseLine);
		Accumulator.report(synch, lock);
		Accumulator.report(synch, atomic);
		Accumulator.report(lock, atomic);
	}

	public static void main(String[] args) {
		int iterations = 5; // Default
		if (args.length > 0) // Optionally change iterations
			iterations = new Integer(args[0]);
		// The first time fills the thread pool:
		System.out.println("Warmup");
		baseLine.timedTest();
		// Now the initial test doesn’t include the cost
		// of starting the threads for the first time.
		// Produce multiple data points:
		for (int i = 0; i < iterations; i++) {
			test();
			Accumulator.cycles *= 2;
		}
		Accumulator.exec.shutdown();
	}
}/* 这是我用CPU是AMD和JDK8的测试数据，结论是synchronized比Lock慢，Atomic性能最优，比BaseLine的还屌
Warmup
BaseLine     :      64048271
============================
Cycles       :         50000
BaseLine     :      51013032
synchronized :     108960662
Lock         :      92848477
Atomic       :      55912105
synchronized/BaseLine : 2.14
Lock/BaseLine         : 1.82
Atomic/BaseLine       : 1.10
synchronized/Lock     : 1.17
synchronized/Atomic   : 1.95
Lock/Atomic           : 1.66
============================
Cycles       :        100000
BaseLine     :     134804239
synchronized :     238426449
Lock         :     177849029
Atomic       :     101590858
synchronized/BaseLine : 1.77
Lock/BaseLine         : 1.32
Atomic/BaseLine       : 0.75
synchronized/Lock     : 1.34
synchronized/Atomic   : 2.35
Lock/Atomic           : 1.75
============================
Cycles       :        200000
BaseLine     :     265685092
synchronized :     472555305
Lock         :     306852979
Atomic       :     206686730
synchronized/BaseLine : 1.78
Lock/BaseLine         : 1.15
Atomic/BaseLine       : 0.78
synchronized/Lock     : 1.54
synchronized/Atomic   : 2.29
Lock/Atomic           : 1.48
============================
Cycles       :        400000
BaseLine     :     413710581
synchronized :     821800880
Lock         :     609943800
Atomic       :     395644543
synchronized/BaseLine : 1.99
Lock/BaseLine         : 1.47
Atomic/BaseLine       : 0.96
synchronized/Lock     : 1.35
synchronized/Atomic   : 2.08
Lock/Atomic           : 1.54
============================
Cycles       :        800000
BaseLine     :     820517502
synchronized :    1812149448
Lock         :    1220477460
Atomic       :     817993454
synchronized/BaseLine : 2.21
Lock/BaseLine         : 1.49
Atomic/BaseLine       : 1.00
synchronized/Lock     : 1.48
synchronized/Atomic   : 2.22
Lock/Atomic           : 1.49
*/

