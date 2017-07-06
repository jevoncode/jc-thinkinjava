package com.jc.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 大端模式和小端模式的ByteBuffer，默认是大端模式
 * 
 * 端模式（Endian）的这个词出自Jonathan
 * Swift书写的《格列佛游记》。这本书根据将鸡蛋敲开的方法不同将所有的人分为两类，从圆头开始将鸡蛋敲开的人被归为Big
 * Endian，从尖头开始将鸡蛋敲开的人被归为Littile
 * Endian。小人国的内战就源于吃鸡蛋时是究竟从大头（Big-Endian）敲开还是从小头（Little-Endian）敲开。在计算机业Big
 * Endian和Little Endian也几乎引起一场战争。在计算机业界，Endian表示数据在存储器中的存放顺序。采用大端方式
 * 进行数据存放符合人类的正常思维，而采用小端方式进行数据存放利于计算机处理。
 * 
 * @author jevoncode
 *
 */
public class Endians {
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
		bb.asCharBuffer().put("abcdef");
		System.out.println(Arrays.toString(bb.array()));
		bb.rewind();
		bb.order(ByteOrder.BIG_ENDIAN);
		bb.asCharBuffer().put("abcdef");
		System.out.println(Arrays.toString(bb.array()));
		bb.rewind();
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.asCharBuffer().put("abcdef");
		System.out.println(Arrays.toString(bb.array()));
	}
}
