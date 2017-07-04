package com.jc.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 带缓冲功能的Reader
 * BufferedReader默认缓冲大小是char[8192]
 * 第一次读取readLine()时，会将缓冲填满
 * @author jevoncode
 *
 */
public class BufferedInputFile {
	// Throw exceptions to console:
	public static String read(String filename) throws IOException {
		// Reading input by lines:
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = in.readLine()) != null)
			sb.append(s + "\n");
		in.close();
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		System.out.print(read("src/main/java/com/jc/io/BufferedInputFile.java"));
	}
}