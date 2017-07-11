package com.jc.annotations;

import java.io.FileInputStream;
import java.io.IOException;

import com.jc.io.util.OSExecute;

/**
 * 使用断言且嵌入式的测试
 * 
 * 需打开vm的断言功能才会报AssertionError异常，不然不会报异常。
 * @author jevoncode
 *
 */
public class AtUnitExample2 {
	public String methodOne() {
		return "This is methodOne";
	}

	public int methodTwo() {
		System.out.println("This is methodTwo");
		return 2;
	}

	@Test
	void assertExample() {
		assert methodOne().equals("This is methodOne");
	}

	@Test
	void assertFailureExample() {
		assert 1 == 2 : "What a surprise!";
	}

	@Test
	void exceptionExample() throws IOException {
		new FileInputStream("nofile.txt"); // Throws
	}

	@Test
	boolean assertAndReturn() {
		// Assertion with message:
		assert methodTwo() == 2 : "methodTwo must equal 2";
		return methodOne().equals("This is methodOne");
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitExample2");
	}
}