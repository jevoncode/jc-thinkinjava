package com.jc.containers;

import java.util.LinkedHashMap;

import com.jc.containers.util.CountingMapData;
/**
 * LinkedHashMap默认是按插入顺序，如果构造方法第二个参数为true，则使用LRU算法的查询顺序
 * @author jevoncode
 *
 */
public class LinkedHashMapDemo {
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<Integer, String>(new CountingMapData(9));
		System.out.println(linkedMap);
		linkedMap = new LinkedHashMap<Integer, String>(16, 0.75f, true); //true表明用LRU算法（Least-recently-used order）
		linkedMap.putAll(new CountingMapData(9));
		System.out.println(linkedMap);
		for (int i = 0; i < 6; i++) // Cause accesses:
			linkedMap.get(i);
		System.out.println(linkedMap);
		linkedMap.get(0);
		System.out.println(linkedMap);
	}
}