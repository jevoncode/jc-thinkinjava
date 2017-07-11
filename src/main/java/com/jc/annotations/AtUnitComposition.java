package com.jc.annotations;

import com.jc.io.util.OSExecute;
/**
 * 使用组合来修改AtUnitExample1进行非嵌入式的测试
 * @author jevoncode
 *
 */
public class AtUnitComposition {
	AtUnitExample1 testObject = new AtUnitExample1();

	@Test
	boolean _methodOne() {
		return testObject.methodOne().equals("This is methodOne");
	}

	@Test
	boolean _methodTwo() {
		return testObject.methodTwo() == 2;
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitComposition");
	}
}