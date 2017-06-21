package com.jc.arrays;

import java.util.Arrays;

/**
 * 数组不能直接输出，不然就是对象的地址了。
 * 需要使用Arrays.toString()，二维数组则Arrays.deepToString()
 * @author jevoncode
 *
 */
public class MultidimensionalPrimitiveArray {
	public static void main(String[] args) {
		int[][] a = { { 1, 2, 3, }, { 4, 5, 6, }, };
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.deepToString(a));
	}
}/*
[[I@15db9742, [I@6d06d69c]
[[1, 2, 3], [4, 5, 6]]
*/