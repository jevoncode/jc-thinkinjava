package com.jc.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * DataInputStream的判断文件结尾的优雅方式
 * @author jevoncode
 *
 */
public class TestEOF {
	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("src/main/java/com/jc/io/TestEOF.java")));
		while (in.available() != 0)
			System.out.print((char) in.readByte());
	}
}