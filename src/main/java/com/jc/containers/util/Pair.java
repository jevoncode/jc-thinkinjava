package com.jc.containers.util;
/**
 * 字段都是final，说明此类只用于做只读的DTO或叫消息、信使（read-only Data Transfer Object or Messager）
 * @author jevoncode
 *
 */
public class Pair<K, V> {
	public final K key;
	public final V value;

	public Pair(K k, V v) {
		key = k;
		value = v;
	}
} 