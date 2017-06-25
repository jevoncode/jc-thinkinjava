package com.jc.arrays;

import java.util.Arrays;
import java.util.Collections;

import com.jc.arrays.util.Generated;
import com.jc.arrays.util.RandomGenerator;

/**
 * 数组的多种排序例子，Java自带排序算法是基本类型是“快速排序”，而引用类型则是“稳定归并排序”
 * @author jevoncode
 *
 */
public class StringSorting {
	public static void main(String[] args) {
		String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
		System.out.println("Before sort: " + Arrays.toString(sa));
		Arrays.sort(sa);
		System.out.println("After sort: " + Arrays.toString(sa));
		Arrays.sort(sa, Collections.reverseOrder());
		System.out.println("Reverse sort: " + Arrays.toString(sa));
		Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
		System.out.println("Case-insensitive sort: " + Arrays.toString(sa));
	}
}