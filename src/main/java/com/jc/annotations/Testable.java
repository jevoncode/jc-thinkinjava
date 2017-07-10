package com.jc.annotations;

/**
 * 注解，也被称为元数据（metadata）
 * 
 * @author jevoncode
 *
 */
public class Testable {
	public void execute() {
		System.out.println("Executing..");
	}

	@Test
	void testExecute() {
		execute();
	}
}
