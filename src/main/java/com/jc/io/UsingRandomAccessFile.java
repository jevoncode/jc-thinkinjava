package com.jc.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * DataInputStream的高级版RandomAccessFile，可以指定位置读取
 * 但缺点还是跟DataInputStream一样，必须清晰文件内容的格式
 * @author jevoncode
 *
 */
public class UsingRandomAccessFile {
	static String file = "rtest.dat";

	static void display() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++)
			System.out.println("Value " + i + ": " + rf.readDouble());
		System.out.println(rf.readUTF());
		rf.close();
	}

	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < 7; i++)
			rf.writeDouble(i * 1.414);
		rf.writeUTF("The end of the file");
		rf.close();
		display();
		rf = new RandomAccessFile(file, "rw");
		rf.seek(5 * 8); //一个Double的长度是8bytes
		rf.writeDouble(47.0001);
		rf.close();
		display();
	}
}