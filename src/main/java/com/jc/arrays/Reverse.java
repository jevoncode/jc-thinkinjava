package com.jc.arrays;

import java.util.Arrays;
import java.util.Collections;

import com.jc.arrays.util.Generated;

/**
 * 不依赖数组自身排序，使用Comparator来排序，有趣的是Array.sort()是重载方法并使用了泛型限制了Comparator
 * 这里使用Collections已经实现好的Comparator来排序
 * @author jevoncode
 *
 */
public class Reverse {
	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a, Collections.reverseOrder());
		System.out.println("after sorting:");
		System.out.println(Arrays.toString(a));
	}
}