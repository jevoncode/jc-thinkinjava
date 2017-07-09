package com.jc.enumerated;

/**
 * 枚举里可以覆盖常量相关的方法
 *  
 * @author jevoncode
 *
 */
public enum OverrideConstantSpecific {
	NUT, BOLT, WASHER {
		void f() {
			System.out.println("Overridden method");
		}
	};
	void f() {
		System.out.println("default behavior");
	}

	public static void main(String[] args) {
		for (OverrideConstantSpecific ocs : values()) {
			System.out.print(ocs + ": ");
			ocs.f();
		}
	}
}