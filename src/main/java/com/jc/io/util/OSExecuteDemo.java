package com.jc.io.util;

/**
 * 进程控制，将操作系统的其他进程的输出，转为java程序的输入
 * @author jevoncode
 *
 */
public class OSExecuteDemo {
	public static void main(String[] args) {
		OSExecute.command("javap D:/OSExecuteDemo.class");
	}
}
