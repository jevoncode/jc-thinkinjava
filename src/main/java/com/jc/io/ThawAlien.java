package com.jc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 模拟网络传输序列化对象——客户端
 * 
 * 这例子展示了，虽然没直接使用Alien类，但使用ObjectInputStream读取Alien对象时，就已经关联了Alien，如果Alien.class不存在则会报java.lang.ClassNotFoundException: com.jc.io.Alien	 
 * @author jevoncode
 *
 */
public class ThawAlien {
	public static void main(String[] args) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("X.file")));
		Object mystery = in.readObject();
		System.out.println(mystery.getClass());
	}
}
/*
 *在IDE执行时由于能找到Alien类，所以输出：class com.jc.io.Alien
 *而手动执行时，并不能找Alien类，于是报错java.lang.ClassNotFoundException: com.jc.io.Alien	 
 */
