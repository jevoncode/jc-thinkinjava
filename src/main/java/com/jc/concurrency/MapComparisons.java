package com.jc.concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jc.arrays.util.CountingGenerator;
import com.jc.containers.util.MapData;

/**
 * SynchronizedMap和ConcurrentHashMap性能对比
 * 结果也跟ListComparisons例子类似，总体ConcurrentHashMap性能比较好，但SynchronizedMap稳定
 * @author jevoncode
 *
 */
abstract class MapTest extends Tester<Map<Integer, Integer>> {
	MapTest(String testId, int nReaders, int nWriters) {
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
					testContainer.put(index, writeData[index]);
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

class SynchronizedHashMapTest extends MapTest {
	Map<Integer, Integer> containerInitializer() {
		return Collections.synchronizedMap(new HashMap<Integer, Integer>(
				MapData.map(new CountingGenerator.Integer(), new CountingGenerator.Integer(), containerSize)));
	}

	SynchronizedHashMapTest(int nReaders, int nWriters) {
		super("Synched HashMap", nReaders, nWriters);
	}
}

class ConcurrentHashMapTest extends MapTest {
	Map<Integer, Integer> containerInitializer() {
		return new ConcurrentHashMap<Integer, Integer>(
				MapData.map(new CountingGenerator.Integer(), new CountingGenerator.Integer(), containerSize));
	}

	ConcurrentHashMapTest(int nReaders, int nWriters) {
		super("ConcurrentHashMap", nReaders, nWriters);
	}
}

public class MapComparisons {
	public static void main(String[] args) {
		Tester.initMain(args);
		new SynchronizedHashMapTest(10, 0);
		new SynchronizedHashMapTest(9, 1);
		new SynchronizedHashMapTest(5, 5);
		new ConcurrentHashMapTest(10, 0);
		new ConcurrentHashMapTest(9, 1);
		new ConcurrentHashMapTest(5, 5);
		Tester.exec.shutdown();
	}
}/* 这是我用CPU是AMD和JDK8的测试数据,结论是总体上ConcurrentHashMap性能比较好，但SynchronizedMap稳定
Picked up _JAVA_OPTIONS:   -Dawt.useSystemAAFontSettings=gasp
Type                             Read time     Write time
Synched HashMap 10r 0w          6314615242              0
Synched HashMap 10r 0w          6552739953              0
Synched HashMap 10r 0w          6238679948              0
Synched HashMap 10r 0w          5295676633              0
Synched HashMap 10r 0w          6394935570              0
Synched HashMap 10r 0w          7557104106              0
Synched HashMap 10r 0w          6259137885              0
Synched HashMap 10r 0w          7254130309              0
Synched HashMap 10r 0w          7245993361              0
Synched HashMap 10r 0w          6720474314              0
Synched HashMap 9r 1w           6033900377      730780772
readTime + writeTime =          6764681149
Synched HashMap 9r 1w           6519390624      667321014
readTime + writeTime =          7186711638
Synched HashMap 9r 1w           6425619840      662361238
readTime + writeTime =          7087981078
Synched HashMap 9r 1w           6048922904      729955929
readTime + writeTime =          6778878833
Synched HashMap 9r 1w           6758933842      847703123
readTime + writeTime =          7606636965
Synched HashMap 9r 1w           6106177778      663610276
readTime + writeTime =          6769788054
Synched HashMap 9r 1w           5568767741      693421765
readTime + writeTime =          6262189506
Synched HashMap 9r 1w           5065227588      597514255
readTime + writeTime =          5662741843
Synched HashMap 9r 1w           5180161094      688707297
readTime + writeTime =          5868868391
Synched HashMap 9r 1w           6806102265      708412500
readTime + writeTime =          7514514765
Synched HashMap 5r 5w           4526182921     4056373753
readTime + writeTime =          8582556674
Synched HashMap 5r 5w           4445071741     4164217340
readTime + writeTime =          8609289081
Synched HashMap 5r 5w           4327989119     4332717522
readTime + writeTime =          8660706641
Synched HashMap 5r 5w           3913972598     4085372875
readTime + writeTime =          7999345473
Synched HashMap 5r 5w           4463930078     4389778496
readTime + writeTime =          8853708574
Synched HashMap 5r 5w           4437496712     4022749650
readTime + writeTime =          8460246362
Synched HashMap 5r 5w           3971210593     4320919073
readTime + writeTime =          8292129666
Synched HashMap 5r 5w           4075225075     4150026320
readTime + writeTime =          8225251395
Synched HashMap 5r 5w           3410103479     3877820472
readTime + writeTime =          7287923951
Synched HashMap 5r 5w           4048206065     4144890998
readTime + writeTime =          8193097063
ConcurrentHashMap 10r 0w        1410425802              0
ConcurrentHashMap 10r 0w         325316955              0
ConcurrentHashMap 10r 0w         421490786              0
ConcurrentHashMap 10r 0w         519885994              0
ConcurrentHashMap 10r 0w         437612417              0
ConcurrentHashMap 10r 0w         429130920              0
ConcurrentHashMap 10r 0w         356363449              0
ConcurrentHashMap 10r 0w         359544761              0
ConcurrentHashMap 10r 0w         347335393              0
ConcurrentHashMap 10r 0w         291231287              0
ConcurrentHashMap 9r 1w          398660374       82471540
readTime + writeTime =           481131914
ConcurrentHashMap 9r 1w          330204948       59731588
readTime + writeTime =           389936536
ConcurrentHashMap 9r 1w          383293708       69921014
readTime + writeTime =           453214722
ConcurrentHashMap 9r 1w          251873729       55544545
readTime + writeTime =           307418274
ConcurrentHashMap 9r 1w          213356809       50190553
readTime + writeTime =           263547362
ConcurrentHashMap 9r 1w          221239132       47891130
readTime + writeTime =           269130262
ConcurrentHashMap 9r 1w          258430911       57201546
readTime + writeTime =           315632457
ConcurrentHashMap 9r 1w          419434527       76866293
readTime + writeTime =           496300820
ConcurrentHashMap 9r 1w          280767693       58450092
readTime + writeTime =           339217785
ConcurrentHashMap 9r 1w          211394950       48550513
readTime + writeTime =           259945463
ConcurrentHashMap 5r 5w          168408608     2013730245
readTime + writeTime =          2182138853
ConcurrentHashMap 5r 5w          121188244     1741401912
readTime + writeTime =          1862590156
ConcurrentHashMap 5r 5w           77808871     1869771565
readTime + writeTime =          1947580436
ConcurrentHashMap 5r 5w          105308703     1825639509
readTime + writeTime =          1930948212
ConcurrentHashMap 5r 5w           87372569     1803198670
readTime + writeTime =          1890571239
ConcurrentHashMap 5r 5w           95717198     1806544892
readTime + writeTime =          1902262090
ConcurrentHashMap 5r 5w          126133436     2132299788
readTime + writeTime =          2258433224
ConcurrentHashMap 5r 5w          130787910     1871011943
readTime + writeTime =          2001799853
ConcurrentHashMap 5r 5w          102820811     1800584048
readTime + writeTime =          1903404859
ConcurrentHashMap 5r 5w          118266795     1901701163
readTime + writeTime =          2019967958
*/