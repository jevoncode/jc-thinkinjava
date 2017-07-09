package com.jc.io;

import java.util.prefs.Preferences;

/**
 * 依赖操作系统的特型储存数据如windows的注册列表，大小有限制为不能存储超过8k
 * 
 * @author jevoncode
 *
 */
public class PreferencesDemo {
	public static void main(String[] args) throws Exception {
		Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);

		/**
		 * 这一段运行一次就可以注释后再执行 begin
		 */
		prefs.put("Location", "Oz");
		prefs.put("Footwear", "Ruby Slippers");
		prefs.putInt("Companions", 4);
		prefs.putBoolean("Are there witches?", true);
		int usageCount = prefs.getInt("UsageCount", 0);
		usageCount++;
		prefs.putInt("UsageCount", usageCount);

		/**
		 * 这一段运行一次就可以注释后再执行 end
		 */

		for (String key : prefs.keys())
			System.out.println(key + ": " + prefs.get(key, null));
		// You must always provide a default value:
		System.out.println("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
	}
}
