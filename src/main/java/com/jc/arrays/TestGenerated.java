package com.jc.arrays;

import java.util.Arrays;

import com.jc.arrays.util.CountingGenerator;
import com.jc.arrays.util.Generated;
/**
 * 测试工具类Generated（生成数组工具类）
 * @author jevoncode
 *
 */
public class TestGenerated {
	public static void main(String[] args) {
		Integer[] a = { 9, 8, 7, 6 };
		System.out.println(Arrays.toString(a));
		a = Generated.array(a, new CountingGenerator.Integer());
		System.out.println(Arrays.toString(a));
		Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
		System.out.println(Arrays.toString(b));
	}
}
