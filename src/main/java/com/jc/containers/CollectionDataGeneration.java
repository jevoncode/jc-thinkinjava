package com.jc.containers;

import java.util.ArrayList;
import java.util.HashSet;

import com.jc.arrays.util.RandomGenerator;
import com.jc.containers.util.CollectionData;
/**
 * 使用CollectionData填充集合的例子
 * @author jevoncode
 *
 */
public class CollectionDataGeneration {
	public static void main(String[] args) {
		System.out.println(new ArrayList<String>(CollectionData.list(new RandomGenerator.String(9), 10)));
		System.out.println(new HashSet<Integer>(new CollectionData<Integer>(new RandomGenerator.Integer(), 10)));
	}
}