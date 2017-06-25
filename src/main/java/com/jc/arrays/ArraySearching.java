package com.jc.arrays;

import java.util.Arrays;

import com.jc.arrays.util.Generated;
import com.jc.arrays.util.RandomGenerator;
import com.jc.generic.util.Generator;

/**
 * 数组搜索Arrays.binarySearch()，前提是必须排好序
 * 找到时，值大于或等于0
 * 找不到则不是跟List那样返回-1，而是大于要找的值的位置-1，如果都没大于要找的值则为length-1
 * @author jevoncode
 *
 */
public class ArraySearching {
	public static void main(String[] args) {
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
		int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
		Arrays.sort(a); //!!
		System.out.println("Sorted array: " + Arrays.toString(a));
		while (true) {
			int r = gen.next();
			int location = Arrays.binarySearch(a, r);
			if (location >= 0) {
				System.out.println("Location of " + r + " is " + location + ", a[" + location + "] = " + a[location]);
				break; // Out of while loop
			}
		}
	}
}
