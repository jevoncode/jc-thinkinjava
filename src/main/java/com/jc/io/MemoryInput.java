package com.jc.io;

import java.io.IOException;
import java.io.StringReader;
/**
 * 使用例子BufferedInputFile来读取所有的字符串，然后创建个StringReader，以内存做输入
 * @author jevoncode
 *
 */
public class MemoryInput {
	public static void main(String[] args) throws IOException {
		StringReader in = new StringReader(BufferedInputFile.read("src/main/java/com/jc/io/MemoryInput.java"));
		int c;
		while ((c = in.read()) != -1)
			System.out.print((char) c);
	}
}
