package com.jc.annotations;

import java.io.IOException;
import java.io.PrintWriter;

import com.jc.annotations.atunit.TestObjectCleanup;
import com.jc.annotations.atunit.TestObjectCreate;
import com.jc.annotations.atunit.TestProperty;
import com.jc.io.util.OSExecute;
/**
 * 带清除效果的测试，每个方法测试完都会执行@TestObjectCleanup注解的方法
 * 
 * 这个测试方法也有点乱，跟业务耦合太高了
 * @author jevoncode
 *
 */
public class AtUnitExample5 {
	private String text;

	public AtUnitExample5(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}

	@TestProperty
	static PrintWriter output;
	@TestProperty
	static int counter;

	@TestObjectCreate
	static AtUnitExample5 create() {
		String id = Integer.toString(counter++);
		try {
			output = new PrintWriter("Test" + id + ".txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new AtUnitExample5(id);
	}

	@TestObjectCleanup
	static void cleanup(AtUnitExample5 tobj) {
		System.out.println("Running cleanup");
		output.close();
	}

	@Test
	boolean test1() {
		output.print("test1");
		return true;
	}

	@Test
	boolean test2() {
		output.print("test2");
		return true;
	}

	@Test
	boolean test3() {
		output.print("test3");
		return true;
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitExample5");
	}
}
