package com.jc.arrays;

import java.util.Arrays;

/**
 * 非基本类型的粗糙数组
 * @author jevoncode
 *
 */
public class MultidimensionalObjectArrays {
	public static void main(String[] args) {
		BerylliumSphere[][] spheres = { { new BerylliumSphere(), new BerylliumSphere() },
				{ new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere() },
				{ new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(),
						new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere() }, };
		System.out.println(Arrays.deepToString(spheres));
	}
}