package com.jc.annotations;

import java.util.HashSet;

import com.jc.io.util.OSExecute;
/**
 * 使用断言且非嵌入式的测试
 * 
 * @author jevoncode
 *
 */
public class HashSetTest {
	HashSet<String> testObject = new HashSet<String>();

	@Test
	void initialization() {
		assert testObject.isEmpty();
	}

	@Test
	void _contains() {
		testObject.add("one");
		assert testObject.contains("one");
	}

	@Test
	void _remove() {
		testObject.add("one");
		testObject.remove("one");
		assert testObject.isEmpty();
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/HashSetTest");
	}
}