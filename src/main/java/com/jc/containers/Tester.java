package com.jc.containers;

import java.util.List;

/**
 * 测试框架的测试内容接口，initializes需被覆盖产生数据并填充进集合中
 * @author jevoncode
 *
 */
public class Tester<C> {
	public static int fieldWidth = 8;
	public static TestParam[] defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 5000, 10000, 500);
	//百万测试时明显看到性能问题
//	public static TestParam[] defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 5000, 10000, 500, 100000, 500, 1000000, 500);

	// Override this to modify pre-test initialization:
	protected C initialize(int size) { //初始化数据，由子类实现，填充进接口中，我觉得还是定义为抽象好
		return container;
	}

	protected C container;
	private String headline = "";
	private List<Test<C>> tests;  //测试内容

	private static String stringField() {
		return "%" + fieldWidth + "s";
	}

	private static String numberField() {
		return "%" + fieldWidth + "d";
	}
	private static String numberField2() {
		return "%" + (fieldWidth-2) + "dns";
	}

	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private TestParam[] paramList = defaultParams;

	public Tester(C container, List<Test<C>> tests) {
		this.container = container;
		this.tests = tests;
		if (container != null)
			headline = container.getClass().getSimpleName();
	}

	/**
	 * 
	 * @param container
	 * @param tests  测试内容
	 * @param paramList  自定义测试参数
	 */
	public Tester(C container, List<Test<C>> tests, TestParam[] paramList) {
		this(container, tests);
		this.paramList = paramList;
	}

	public void setHeadline(String newHeadline) {
		headline = newHeadline;
	}

	// Generic methods for convenience :
	public static <C> void run(C cntnr, List<Test<C>> tests) {
		new Tester<C>(cntnr, tests).timedTest();
	}

	public static <C> void run(C cntnr, List<Test<C>> tests, TestParam[] paramList) {
		new Tester<C>(cntnr, tests, paramList).timedTest();
	}

	private void displayHeader() {
		// Calculate width and pad with '-':
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headline.length() - 1; //减1时为了添加' '
		StringBuilder head = new StringBuilder(width);
		for (int i = 0; i < dashLength / 2; i++)
			head.append('-');
		head.append(' ');
		head.append(headline);
		head.append(' ');
		for (int i = 0; i < dashLength / 2; i++)
			head.append('-');
		System.out.println(head);
		// Print column headers:
		System.out.format(sizeField, "size");
		for (Test test : tests)
			System.out.format(stringField(), test.name);
		System.out.println();
	}

	// Run the tests for this container:
	public void timedTest() {
		displayHeader();
		for (TestParam param : paramList) {
			System.out.format(sizeField, param.size);
			for (Test<C> test : tests) {
				C kontainer = initialize(param.size);
				long start = System.nanoTime();
				// Call the overriden method:
				int reps = test.test(kontainer, param);
				long duration = System.nanoTime() - start;
				long timePerRep = duration / reps; // Nanoseconds
				System.out.format(numberField2(), timePerRep);
			}
			System.out.println();
		}
	}
}