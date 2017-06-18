package com.jc.generic;

import java.util.ArrayList;
import java.util.List;
/**
 * 泛型T在这里就有点意义，保证方法内和类中使用的类型的内部一致性
 * @author jevoncode
 *
 */
public class FilledListMaker<T> {
	List<T> create(T t, int n) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < n; i++)
			result.add(t);
		return result;
	}

	public static void main(String[] args) {
		FilledListMaker<String> stringMaker = new FilledListMaker<String>();
		List<String> list = stringMaker.create("Hello", 4);
		System.out.println(list);
	}
}
