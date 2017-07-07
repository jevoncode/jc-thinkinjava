package com.jc.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 压缩多个文件，试过用windows压缩软件解压会出现路径问题，看来暂时只能java去解压
 * @author jevoncode
 *
 */
public class ZipCompress {
	public static void main(String[] args) throws IOException {
		FileOutputStream f = new FileOutputStream("test.zip");
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(csum);  
		BufferedOutputStream out = new BufferedOutputStream(zos);
		zos.setComment("A test of Java Zipping");
		// No corresponding getComment(), though.
		for (String arg : args) {
			System.out.println("Writing file " + arg);
			BufferedReader in = new BufferedReader(new FileReader(arg));
			zos.putNextEntry(new ZipEntry(arg)); //这玩法有点6，用ZipOutputStream添加Entry，但却是用BufferedOutputStream进行输出
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
			in.close();
			out.flush();
		}
		out.close();
		// Checksum valid only after the file has been closed!
		System.out.println("Checksum: " + csum.getChecksum().getValue());
		// Now extract the files:
		System.out.println("Reading file");
		
		//第一种方法读取zip压缩文件里的内容
		FileInputStream fi = new FileInputStream("test.zip");
		CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		BufferedInputStream bis = new BufferedInputStream(in2);
		ZipEntry ze;
		while ((ze = in2.getNextEntry()) != null) {  //这也一样，用ZipInputStream获取Entry，但却是用BufferedInputStream进行输入
			System.out.println("Reading file " + ze);
			int x;
			while ((x = bis.read()) != -1)
				System.out.write(x);
		}
		if (args.length == 1)
			System.out.println("Checksum: " + csumi.getChecksum().getValue());
		bis.close();
		
		//第二种方法读取zip压缩文件里的内容
		// Alternative way to open and read Zip files:
		ZipFile zf = new ZipFile("test.zip");
		Enumeration e = zf.entries();
		while (e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry) e.nextElement();
			System.out.println("File: " + ze2);
			// ... and extract the data as before
		}
		/* if(args.length == 1) */
	}
}