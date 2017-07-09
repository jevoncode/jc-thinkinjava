package com.jc.enumerated;

import java.text.DateFormat;
import java.util.Date;
/**
 * 常量相关的方法——在枚举里实现抽象方法
 * @author jevoncode
 *
 */
public enum ConstantSpecificMethod {
	DATE_TIME {

		String getInfo() {
			return DateFormat.getDateInstance().format(new Date());
		}

	},
	CLASSPATH {

		String getInfo() {
			return System.getenv("CLASSPATH");
		}

	},
	VERSION {

		String getInfo() {
			return System.getProperty("java.version");
		}

	};

	abstract String getInfo();

	public static void main(String[] args) {
		for (ConstantSpecificMethod csm : values())
			System.out.println(csm.getInfo());
	}
}
