package com.jc.io;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;
/**
 * Channel可以锁文件，也可以锁文件的部分
 * @author jevoncode
 *
 */
public class FileLocking {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("file.txt");
		FileLock fl = fos.getChannel().tryLock();
		if (fl != null) {
			System.out.println("Locked File");
			TimeUnit.MILLISECONDS.sleep(30000);
			fl.release();
			System.out.println("Released Lock");
		}
		fos.close();
	}
}