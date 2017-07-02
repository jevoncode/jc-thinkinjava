package com.jc.containers;
/**
 * 一个错误微基测试(microbenchmark)的例子，要严谨地分析要做的测试和这测试的局限性
 * @author jevoncode
 *
 */
public class RandomBounds {
	static void usage() {
		System.out.println("Usage:");
		System.out.println("\tRandomBounds lower");
		System.out.println("\tRandomBounds upper");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length != 1)
			usage();
		if (args[0].equals("lower")) {
			while (Math.random() != 0.0)
				; // Keep trying
			System.out.println("Produced 0.0!");
		} else if (args[0].equals("upper")) {
			while (Math.random() != 1.0)
				; // Keep trying
			System.out.println("Produced 1.0!");
		} else
			usage();
	}
}