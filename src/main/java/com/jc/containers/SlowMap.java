package com.jc.containers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jc.designpattern.containers.Countries;
/**
 * 这也是用数组实现map，但跟AssociativeArray例子不一样，这个是遵守Map的规范
 * entrySet()方法返回Map.Entry对象集
 * 但缺点是性能不好，线性查询
 * @author jevoncode 
 */
public class SlowMap<K, V> extends AbstractMap<K, V> {
	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();

	public V put(K key, V value) {
		V oldValue = get(key); // The old value or null
		if (!keys.contains(key)) {
			keys.add(key);
			values.add(value);
		} else
			values.set(keys.indexOf(key), value);
		return oldValue;
	}

	public V get(Object key) { // key is type Object, not K
		if (!keys.contains(key))
			return null;
		return values.get(keys.indexOf(key));
	}

	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		while (ki.hasNext())
			set.add(new MapEntry<K, V>(ki.next(), vi.next()));
		return set;
	}

	public static void main(String[] args) {
		SlowMap<String, String> m = new SlowMap<String, String>();
		m.putAll(Countries.capitals(15));
		System.out.println(m);
		System.out.println(m.get("BULGARIA"));
		System.out.println(m.entrySet());
	}
}