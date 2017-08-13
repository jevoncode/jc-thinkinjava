package com.jc.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 站在对象角度的一种线程模型：活动对象（active object）
 * @author jevoncode
 *
 */
public class ActiveObjectDemo {
	private ExecutorService ex = Executors.newSingleThreadExecutor();
	private Random rand = new Random(47);

	// Insert a random delay to produce the effect
	// of a calculation time:
	private void pause(int factor) {
		try {
			TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}

	public Future<Integer> calculateInt(final int x, final int y) {
		return ex.submit(new Callable<Integer>() {
			public Integer call() {
				System.out.println("starting " + x + " + " + y);
				pause(500);
				return x + y;
			}
		});
	}

	public Future<Float> calculateFloat(final float x, final float y) {
		return ex.submit(new Callable<Float>() {
			public Float call() {
				System.out.println("starting " + x + " + " + y);
				pause(2000);
				return x + y;
			}
		});
	}

	public void shutdown() {
		ex.shutdown();
	}

	public static void main(String[] args) {
		ActiveObjectDemo d1 = new ActiveObjectDemo();
		// Prevents ConcurrentModificationException:
		List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
		for (float f = 0.0f; f < 1.0f; f += 0.2f)
			results.add(d1.calculateFloat(f, f));
		for (int i = 0; i < 5; i++)
			results.add(d1.calculateInt(i, i));
		System.out.println("All asynch calls made");
		while (results.size() > 0) {
			for (Future<?> f : results)  //对集合遍历并删除，所以才使用CopyOnWriteArrayList这种List
				if (f.isDone()) {
					try {
						System.out.println(f.get());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					results.remove(f);
				}
		}
		d1.shutdown();
	}
}