package com.jc.arrays;

import java.util.Arrays;

/**
 * 数组复制，也经常可以在容器的类中看到这方法的使用
 * @author jevoncode
 *
 */
public class CopyingArrays {
	public static void main(String[] args) {
		int[] i = new int[7];
		int[] j = new int[10];
		Arrays.fill(i, 47);
		Arrays.fill(j, 99);
		System.out.println("i = " + Arrays.toString(i));
		System.out.println("j = " + Arrays.toString(j));
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println("j = " + Arrays.toString(j));
		int[] k = new int[5];
		Arrays.fill(k, 103);
		System.arraycopy(i, 0, k, 0, k.length);
		System.out.println("k = " + Arrays.toString(k));
		Arrays.fill(k, 103);
		System.arraycopy(k, 0, i, 0, k.length);
		System.out.println("i = " + Arrays.toString(i));
		// Objects:
		Integer[] u = new Integer[10];
		Integer[] v = new Integer[5];
		Arrays.fill(u, new Integer(47));
		Arrays.fill(v, new Integer(99));
		System.out.println("u = " + Arrays.toString(u));
		System.out.println("v = " + Arrays.toString(v));
		System.arraycopy(v, 0, u, u.length / 2, v.length);
		System.out.println("u = " + Arrays.toString(u));
	}
}