package com.jc.containers;

/**
 * hashcode的生成，字符串的默认hashcode是根据字符串内容生成
 * @author jevoncode
 *
 */
public class StringHashCode {
	public static void main(String[] args) {
		String[] hellos = "Hello Hello".split(" ");
		System.out.println(hellos[0].hashCode());
		System.out.println(hellos[1].hashCode());
	}
}
