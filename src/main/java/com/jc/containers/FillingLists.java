package com.jc.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 容器也跟数组类似，有工具来填充
 * @author jevoncode
 *
 */
class StringAddress {
	private String s;

	public StringAddress(String s) {
		this.s = s;
	}

	public String toString() {
		return super.toString() + " " + s;
	}
}

public class FillingLists {
	public static void main(String[] args) {
		//放入4个new StringAddress("Hello")的引用，注意是引用，都是通过对象
		List<StringAddress> list = new ArrayList<StringAddress>(Collections.nCopies(4, new StringAddress("Hello")));
		System.out.println(list);
		
		//只会填充当前list的长度，替换但不增不减
		Collections.fill(list, new StringAddress("World!"));
		System.out.println(list);
	}
}