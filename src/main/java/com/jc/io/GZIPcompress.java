package com.jc.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIPInputStream压缩例子，这个屌，字节流编程字符流来操作
 * @author jevoncode
 *
 */
public class GZIPcompress {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println(
					"Usage: \nGZIPcompress file\n" + "\tUses GZIP compression to compress " + "the file to test.gz");
			System.exit(1);
		}
		BufferedReader in = new BufferedReader(new FileReader(args[0])); //读要被压缩的文件，FileReader不支持文件夹，原生代码写着的，java源码是看不出
		BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz"))); //创建压缩文件的输出流
		System.out.println("Writing file");
		int c;
		while ((c = in.read()) != -1)
			out.write(c);
		in.close();
		out.close();
		System.out.println("Reading file");
		BufferedReader in2 = new BufferedReader(
				new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz")))); //读取压缩文件
		String s;
		while ((s = in2.readLine()) != null)  //!!!读的不是压缩文件，而是读被压缩的文件，所以被压缩的文件不能是二进制文件，不然显示就很怪了
			System.out.println(s);
	}
}