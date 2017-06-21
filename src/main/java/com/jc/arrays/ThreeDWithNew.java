package com.jc.arrays;

import java.util.Arrays;
/**
 * 三维数组
 * @author jevoncode
 *
 */
public class ThreeDWithNew {
	public static void main(String[] args) {
		// 3-D array with fixed length:
		int[][][] a = new int[2][2][4];
		System.out.println(Arrays.deepToString(a));
	}
}