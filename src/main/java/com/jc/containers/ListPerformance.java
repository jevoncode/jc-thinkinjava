package com.jc.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import com.jc.arrays.util.CountingGenerator;
import com.jc.arrays.util.Generated;
import com.jc.containers.util.CountingIntegerList;
/**
 * List相关的容器测试，结论是:
 * 
 * Array生成的List，由于没有增删功能，所以集合长度多少都没关系
 * ArrayList对象，新增和遍历在集合任何长度都没影响，但插入和删除，性能就慢咯，特别是百万级别以上的数据量（长度）
 * LinkedList对象，在百万级别的数据量以下时，确实比ArrayList慢那么一丢丢，但不是说差别很大，查询的情况确实比ArrayList糟糕挺多。不过相比之下，在百万级别数据量以上时，插入和删除，那可以比ArrayList好太多了，两者没得比。
 * Vector跟ArrayList类似，只是Vector采用同步操作，所以两者性能差不了多少（在单线程情况下）。
 * 队列是另外测试，没啥可比性
 * @author jevoncode
 *
 */
public class ListPerformance {
	static Random rand = new Random();
	static int reps = 1000; //重复次数
	static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qTests = new ArrayList<Test<LinkedList<Integer>>>();
	static {
		tests.add(new Test<List<Integer>>("add") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < listSize; j++)
						list.add(j);
				}
				return loops * listSize;
			}
		});
		tests.add(new Test<List<Integer>>("get") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for (int i = 0; i < loops; i++)
					list.get(rand.nextInt(listSize));
				return loops; //返回重复次数，方便上面一层计算每次的时间
			}
		});
		tests.add(new Test<List<Integer>>("set") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for (int i = 0; i < loops; i++)
					list.set(rand.nextInt(listSize), 47);
				return loops;  //返回重复次数，方便上面一层计算每次的时间
			}
		});
		tests.add(new Test<List<Integer>>("iteradd") {
			int test(List<Integer> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size() / 2;
				ListIterator<Integer> it = list.listIterator(half);
				for (int i = 0; i < LOOPS; i++)
					it.add(47);
				return LOOPS;
			}
		});
		tests.add(new Test<List<Integer>>("insert") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				for (int i = 0; i < loops; i++)
					list.add(5, 47); // Minimize random-access cost
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("remove") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while (list.size() > 5)
						list.remove(5); // Minimize random-access cost
				}
				return loops * size;
			}
		});
		// Tests for queue behavior:
		qTests.add(new Test<LinkedList<Integer>>("addFirst") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < size; j++)
						list.addFirst(47);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("addLast") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < size; j++)
						list.addLast(47);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while (list.size() > 0)
						list.removeFirst();
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("rmLast") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while (list.size() > 0)
						list.removeLast();
				}
				return loops * size;
			}
		});
	}

	static class ListTester extends Tester<List<Integer>> {
		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}

		// Fill to the appropriate size before each test:
		@Override
		protected List<Integer> initialize(int size) {
			container.clear();
			container.addAll(new CountingIntegerList(size));
			return container;
		}

		// Convenience method:
		public static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
			new ListTester(list, tests).timedTest();
		}
	}

	public static void main(String[] args) {
		if (args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		// Can only do these two tests on an array:
		Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, tests.subList(1, 3)) { //还可以这样玩覆盖方法，这里是覆盖Tester的initialize()方法
			// This will be called before each test. It
			// produces a non-resizeable array-backed list:
			@Override
			protected List<Integer> initialize(int size) {
				Integer[] ia = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
				return Arrays.asList(ia);
			}
		};
		arrayTest.setHeadline("Array as List");
		arrayTest.timedTest();
		Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
//		Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200, 100000, 10, 1000000, 10);
		if (args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		ListTester.run(new ArrayList<Integer>(), tests);
		ListTester.run(new LinkedList<Integer>(), tests);
		ListTester.run(new Vector<Integer>(), tests);
		Tester.fieldWidth = 12;
		Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(new LinkedList<Integer>(), qTests);
		qTest.setHeadline("Queue tests");
		qTest.timedTest();
	}
}