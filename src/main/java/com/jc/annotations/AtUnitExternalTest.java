package com.jc.annotations;

import com.jc.io.util.OSExecute;
/**
 * 使用继承来修改AtUnitExample1进行测试
 * @author jevoncode
 *
 */
public class AtUnitExternalTest extends AtUnitExample1 {
	@Test
	boolean _methodOne() {
		return methodOne().equals("This is methodOne");
	}

	@Test
	boolean _methodTwo() {
		return methodTwo() == 2;
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitExternalTest");
	}
}
