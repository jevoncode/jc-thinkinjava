package com.jc.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * DataInputStream的例子，使用ByteArrayInputStream做输入
 * 由于DataInputStream不能使用其readByte()返回结果来判断文件结束，故采用异常来处理
 * 
 * DataInputStream数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型
 * @author jevoncode
 *
 */
public class FormattedMemoryInput {
	public static void main(String[] args) throws IOException {
		try {
			DataInputStream in = new DataInputStream(
					new ByteArrayInputStream(BufferedInputFile.read("src/main/java/com/jc/io/FormattedMemoryInput.java").getBytes()));
			while (true)
				System.out.print((char) in.readByte());
		} catch (EOFException e) {
			System.err.println("End of stream");
		}
	}
}
