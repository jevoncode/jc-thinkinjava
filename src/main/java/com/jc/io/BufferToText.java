package com.jc.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Buffer的编码问题
 * @author jevoncode
 *
 */
public class BufferToText {
	private static final int BSIZE = 1024;

	public static void main(String[] args) throws Exception {
		FileChannel fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		fc = new FileInputStream("data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// Doesn’t work:
		/**第1种编码方式，没用，因为上面写的时候就没指定编码格式**/
		System.out.println(buff.asCharBuffer());
		// Decode using this system’s default Charset:
		buff.rewind();
		String encoding = System.getProperty("file.encoding");

		/**第二种编码方式，使用Charset**/
		System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buff));
		
		
		/**第三种，是输入时确定编码格式，不过还是使用ByteBuffer**/
		// Or, we could encode with something that will print:
		fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		// Now try reading again:
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		
		/**第四种，是输入时确定编码格式，跟第三种不同，ByteBuffer转为CharBuffer了
		 * ByteBuffer转为CharBuffer去输出就需要个计算过程，
		 * ByteBuffer分配了24个字节
		 * buff.asCharBuffer().put("Some text"); 只占用了18个字节，一个字符占两位字节
		 * 则剩下的6个字节将转为空字符
		 * 
		 * **/
		// Use a CharBuffer to write through:
		fc = new FileOutputStream("data2.txt").getChannel();
		buff = ByteBuffer.allocate(24); // More than needed
		buff.asCharBuffer().put("Some text");
		System.out.println(buff.asCharBuffer());  //"Some text   "
		fc.write(buff);
		fc.close();
		// Read and display:
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer()); //"Some text   "
	}
}
