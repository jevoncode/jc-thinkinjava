package com.jc.io.util;

/**
 * 进程控制，将操作系统的其他进程的输出，转为java程序的输入
 * 
 * 异常抛出
 * @author jevoncode
 *
 */
public class OSExecuteException extends RuntimeException {
	public OSExecuteException(String why) {
		super(why);
	}
}