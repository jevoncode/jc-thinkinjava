package com.jc.io;

import java.io.PrintWriter;

/**
 * 标准IO，封装System.out
 * @author jevoncode
 *
 */
public class ChangeSystemOut {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("Hello, world");
	}
}
