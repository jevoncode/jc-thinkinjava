package com.jc.annotations;

import com.jc.io.util.OSExecute;

/**
 * 嵌入到被测试的类里测试
 * @author jevoncode
 *
 */
public class AtUnitExample1 {
	public String methodOne() {
		return "This is methodOne";
	}

	public int methodTwo() {
		System.out.println("This is methodTwo");
		return 2;
	}

	@Test
	boolean methodOneTest() {
		return methodOne().equals("This is methodOne");
	}

	@Test
	boolean m2() {
		return methodTwo() == 2;
	}

	@Test
	private boolean m3() {
		return true;
	}

	// Shows output for failure:
	@Test
	boolean failureTest() {
		return false;
	}

	@Test
	boolean anotherDisappointment() {
		return false;
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitExample1");
	}
}
