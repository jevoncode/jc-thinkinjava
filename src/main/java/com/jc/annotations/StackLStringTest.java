package com.jc.annotations;

import com.jc.io.util.OSExecute;

/**
 * 泛型的测试
 * @author jevoncode
 *
 */
public class StackLStringTest extends StackL<String> {
	@Test
	void _push() {
		push("one");
		assert top().equals("one");
		push("two");
		assert top().equals("two");
	}

	@Test
	void _pop() {
		push("one");
		push("two");
		assert pop().equals("two");
		assert pop().equals("one");
	}

	@Test
	void _top() {
		push("A");
		push("B");
		assert top().equals("B");
		assert top().equals("B");
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/StackLStringTest");
	}
}