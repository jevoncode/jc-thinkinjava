package com.jc.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;
/**
 * java.nio.charset.Charset 所支持的编码
 * @author jevoncode
 *
 */
public class AvailableCharSets {
	public static void main(String[] args) {
		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		Iterator<String> it = charSets.keySet().iterator();
		while (it.hasNext()) {
			String csName = it.next();
			System.out.print(csName);
			Iterator aliases = charSets.get(csName).aliases().iterator();
			if (aliases.hasNext())
				System.out.print(": ");
			while (aliases.hasNext()) {
				System.out.print(aliases.next());
				if (aliases.hasNext())
					System.out.print(", ");
			}
			System.out.println();
		}
	}
}
