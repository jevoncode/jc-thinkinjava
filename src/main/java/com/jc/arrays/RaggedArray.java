package com.jc.arrays;

import java.util.Arrays;
import java.util.Random;

/**
 * 粗糙数组(ragged array)，在多维数组里定义时指定第一维即可
 * @author jevoncode
 *
 */
public class RaggedArray {
	public static void main(String[] args) {
		Random rand = new Random(47);
		// 3-D array with varied-length vectors:
		int[][][] a = new int[rand.nextInt(7)][][];
		System.out.println(Arrays.deepToString(a)); // [null, null, null, null, null, null]
		for (int i = 0; i < a.length; i++) {
			a[i] = new int[rand.nextInt(5)][];
			for (int j = 0; j < a[i].length; j++)
				a[i][j] = new int[rand.nextInt(5)];
		}
		System.out.println(Arrays.deepToString(a));//[[], [[0], [0], [0, 0, 0, 0]], [[], [0, 0], [0, 0]], [[0, 0, 0], [0], [0, 0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0], []], [[0], [], [0]]]
	}
}