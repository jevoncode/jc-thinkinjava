package com.jc.annotations;

import com.jc.annotations.atunit.TestObjectCreate;
import com.jc.io.util.OSExecute;

/**
 * 非默认构造器的测试@TestObjectCreate
 * @author jevoncode
 *
 */
public class AtUnitExample3 {
	private int n;

	public AtUnitExample3(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public String methodOne() {
		return "This is methodOne";
	}

	public int methodTwo() {
		System.out.println("This is methodTwo");
		return 2;
	}

	@TestObjectCreate
	static AtUnitExample3 create() {
		return new AtUnitExample3(47);
	}

	@Test
	boolean initialization() {
		return n == 47;
	}

	@Test
	boolean methodOneTest() {
		return methodOne().equals("This is methodOne");
	}

	@Test
	boolean m2() {
		return methodTwo() == 2;
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitExample3");
	}
}
