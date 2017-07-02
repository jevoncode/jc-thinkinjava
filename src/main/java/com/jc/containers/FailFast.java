package com.jc.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
/**
 * 最常见的错误，迭代时抛出并发异常
 * 生成迭代器后，不能修改集合，除非重新生成迭代器
 * @author jevoncode
 *
 */
public class FailFast {
	public static void main(String[] args) {
		Collection<String> c = new ArrayList<String>();
		Iterator<String> it = c.iterator();
		c.add("An object");
		try {
			String s = it.next();
		} catch (ConcurrentModificationException e) {
			System.out.println(e);
		}
	}
}