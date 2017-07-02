package com.jc.containers;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Soft、Weak、 Phantom的Reference要被回收时会放进ReferenceQueue 
 * 这个例子运行之后可发现：
 * SoftReference的引用迟迟没调用用finalize()
 * WeakReference在System.gc()后就调用用finalize()
 * PhantomReference在System.gc()后就调用用finalize()
 * 还不是很理解，留着吧，这本书也就一小节带过而已，没细说
 * @author jevoncode
 *
 */
public class References {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

	public static void checkQueue() {
		Reference<? extends VeryBig> inq = rq.poll();
		if (inq != null) // PhantomReference要很快被回收，所以checkQueue()以下就进来了，rq队列就有数据
			System.out.println("In queue: " + inq.get());
	}

	public static void main(String[] args) {
		int size = 10;
		// Or, choose size via the command line:
		if (args.length > 0)
			size = new Integer(args[0]);
		LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
			System.out.println("Just created: " + sa.getLast());
			checkQueue();
		}
		LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
			System.out.println("Just created: " + wa.getLast());
			checkQueue();
		}
		SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
		WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));
		System.gc();
		LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i), rq));
			System.out.println("Just created: " + pa.getLast());
			checkQueue();
		}
		int i = 0;
		while (true) {
			if(i++==3)
				System.gc();
			System.out.println(pa); //神奇，finalize()都会回收执行了，还是可以输出
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
