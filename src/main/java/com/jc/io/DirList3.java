package com.jc.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 用匿名内部类优化DirList例子
 * @author jevoncode
 *
 */
public class DirList3 {
	public static void main(final String[] args) {
		File path = new File(".");
		String[] list;
		if (args.length == 0)
			list = path.list();
		else
			list = path.list(new FilenameFilter() {
				private Pattern pattern = Pattern.compile(args[0]);

				public boolean accept(File dir, String name) {
					return pattern.matcher(name).matches();
				}
			});
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list)
			System.out.println(dirItem);
	}
}