package com.jc.generic;

import java.util.ArrayList;
import java.util.List;
/**
 * 如果单从这个例子和擦除来看，好像T的意义不是很大，因为都是object，运行时是不会有String的任何信息
 * @author jevoncode
 *
 */
public class ListMaker<T> {
	List<T> create() {
		return new ArrayList<T>();
	}

	public static void main(String[] args) {
		ListMaker<String> stringMaker = new ListMaker<String>();
		List<String> stringList = stringMaker.create();
	}
} 