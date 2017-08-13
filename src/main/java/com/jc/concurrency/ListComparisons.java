package com.jc.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jc.containers.util.CountingIntegerList;

/**
 * Synched ArrayList 和 CopyOnWriteArrayList的性能对比
 * 总体上CopyOnWriteArrayList更优，但Synched ArrayList更稳定
 * CopyOnWriteArraySet跟CopyOnWriteArrayList类似
 * @author jevoncode
 *
 */
abstract class ListTest extends Tester<List<Integer>> {
	ListTest(String testId, int nReaders, int nWriters) {
		super(testId, nReaders, nWriters);
	}

	class Reader extends TestTask {
		long result = 0;

		void test() {
			for (long i = 0; i < testCycles; i++)
				for (int index = 0; index < containerSize; index++)
					result += testContainer.get(index);
		}

		void putResults() {
			readResult += result;
			readTime += duration;
		}
	}

	class Writer extends TestTask {
		void test() {
			for (long i = 0; i < testCycles; i++)
				for (int index = 0; index < containerSize; index++)
					testContainer.set(index, writeData[index]);
		}

		void putResults() {
			writeTime += duration;
		}
	}

	void startReadersAndWriters() {
		for (int i = 0; i < nReaders; i++)
			exec.execute(new Reader());
		for (int i = 0; i < nWriters; i++)
			exec.execute(new Writer());
	}
}

class SynchronizedArrayListTest extends ListTest {
	List<Integer> containerInitializer() {
		return Collections.synchronizedList(new ArrayList<Integer>(new CountingIntegerList(containerSize)));
	}

	SynchronizedArrayListTest(int nReaders, int nWriters) {
		super("Synched ArrayList", nReaders, nWriters);
	}
}

class CopyOnWriteArrayListTest extends ListTest {
	List<Integer> containerInitializer() {
		return new CopyOnWriteArrayList<Integer>(new CountingIntegerList(containerSize));
	}

	CopyOnWriteArrayListTest(int nReaders, int nWriters) {
		super("CopyOnWriteArrayList", nReaders, nWriters);
	}
}

public class ListComparisons {
	public static void main(String[] args) {
		Tester.initMain(args);
		new SynchronizedArrayListTest(10, 0);
		new SynchronizedArrayListTest(9, 1);
		new SynchronizedArrayListTest(5, 5);
		new CopyOnWriteArrayListTest(10, 0);
		new CopyOnWriteArrayListTest(9, 1);
		new CopyOnWriteArrayListTest(5, 5);
		Tester.exec.shutdown();
	}
}/* 这是我用CPU是AMD和JDK8的测试数据,结论是总体上CopyOnWriteArrayList更优，但Synched ArrayList更稳定
Type                             Read time     Write time
Synched ArrayList 10r 0w        5996468776              0
Synched ArrayList 10r 0w       11386340035              0
Synched ArrayList 10r 0w        5484929893              0
Synched ArrayList 10r 0w        7971696554              0
Synched ArrayList 10r 0w        4843993673              0
Synched ArrayList 10r 0w        6688658239              0
Synched ArrayList 10r 0w        6491860890              0
Synched ArrayList 10r 0w        6174600951              0
Synched ArrayList 10r 0w        6845797804              0
Synched ArrayList 10r 0w        6891304113              0
Synched ArrayList 9r 1w         4234422630      520447436
readTime + writeTime =          4754870066
Synched ArrayList 9r 1w         6060809840      776178111
readTime + writeTime =          6836987951
Synched ArrayList 9r 1w         5668879182      634136549
readTime + writeTime =          6303015731
Synched ArrayList 9r 1w         4740329149      540785371
readTime + writeTime =          5281114520
Synched ArrayList 9r 1w         5629966847      761181278
readTime + writeTime =          6391148125
Synched ArrayList 9r 1w         6188697392      753374829
readTime + writeTime =          6942072221
Synched ArrayList 9r 1w         4240512600      520725147
readTime + writeTime =          4761237747
Synched ArrayList 9r 1w         3942008382      502413126
readTime + writeTime =          4444421508
Synched ArrayList 9r 1w         5120091854      661090954
readTime + writeTime =          5781182808
Synched ArrayList 9r 1w         6741586226      819619241
readTime + writeTime =          7561205467
Synched ArrayList 5r 5w         3147576374     3338811677
readTime + writeTime =          6486388051
Synched ArrayList 5r 5w         2633486290     3061762801
readTime + writeTime =          5695249091
Synched ArrayList 5r 5w         2882531757     3197606675
readTime + writeTime =          6080138432
Synched ArrayList 5r 5w         2675298224     2890736458
readTime + writeTime =          5566034682
Synched ArrayList 5r 5w         2768758652     2836001056
readTime + writeTime =          5604759708
Synched ArrayList 5r 5w         2430379124     2755058570
readTime + writeTime =          5185437694
Synched ArrayList 5r 5w         2543896027     2693443379
readTime + writeTime =          5237339406
Synched ArrayList 5r 5w         3409677097     3727424369
readTime + writeTime =          7137101466
Synched ArrayList 5r 5w         3678360856     3623627909
readTime + writeTime =          7301988765
Synched ArrayList 5r 5w         3339687209     3551991883
readTime + writeTime =          6891679092
CopyOnWriteArrayList 10r 0w      458784611              0
CopyOnWriteArrayList 10r 0w      119204603              0
CopyOnWriteArrayList 10r 0w      101754208              0
CopyOnWriteArrayList 10r 0w      129958452              0
CopyOnWriteArrayList 10r 0w      121927101              0
CopyOnWriteArrayList 10r 0w      108348323              0
CopyOnWriteArrayList 10r 0w      104284581              0
CopyOnWriteArrayList 10r 0w      107545762              0
CopyOnWriteArrayList 10r 0w       78176407              0
CopyOnWriteArrayList 10r 0w      159173785              0
CopyOnWriteArrayList 9r 1w       111507617       75478109
readTime + writeTime =           186985726
CopyOnWriteArrayList 9r 1w       129313692       69663408
readTime + writeTime =           198977100
CopyOnWriteArrayList 9r 1w       165548544       74369167
readTime + writeTime =           239917711
CopyOnWriteArrayList 9r 1w       185477737       73537491
readTime + writeTime =           259015228
CopyOnWriteArrayList 9r 1w       242924514       76054967
readTime + writeTime =           318979481
CopyOnWriteArrayList 9r 1w       128571212       70356622
readTime + writeTime =           198927834
CopyOnWriteArrayList 9r 1w       246877854       78271572
readTime + writeTime =           325149426
CopyOnWriteArrayList 9r 1w       143834913       71698106
readTime + writeTime =           215533019
CopyOnWriteArrayList 9r 1w       109622832       63894987
readTime + writeTime =           173517819
CopyOnWriteArrayList 9r 1w        71953054       55151793
readTime + writeTime =           127104847
CopyOnWriteArrayList 5r 5w        54793853     1668899154
readTime + writeTime =          1723693007
CopyOnWriteArrayList 5r 5w        81540065     1961358613
readTime + writeTime =          2042898678
CopyOnWriteArrayList 5r 5w        98898538     1841792507
readTime + writeTime =          1940691045
CopyOnWriteArrayList 5r 5w        74591410     1673004861
readTime + writeTime =          1747596271
CopyOnWriteArrayList 5r 5w       165545109     2155796576
readTime + writeTime =          2321341685
CopyOnWriteArrayList 5r 5w        84500628     2507408518
readTime + writeTime =          2591909146
CopyOnWriteArrayList 5r 5w        49912029     1945511318
readTime + writeTime =          1995423347
CopyOnWriteArrayList 5r 5w        42034026     1726495569
readTime + writeTime =          1768529595
CopyOnWriteArrayList 5r 5w        71545449     1697923613
readTime + writeTime =          1769469062
CopyOnWriteArrayList 5r 5w        73715435     2394876493
readTime + writeTime =          2468591928

*/