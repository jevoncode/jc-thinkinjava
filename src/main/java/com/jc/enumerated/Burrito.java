package com.jc.enumerated;

import static com.jc.enumerated.Spiciness.*;

/**
 * 枚举可静态倒入，从而不需要引用枚举类名
 * @author jevoncode
 *
 */
public class Burrito {
	Spiciness degree;

	public Burrito(Spiciness degree) {
		this.degree = degree;
	}

	public String toString() {
		return "Burrito is " + degree;
	}

	public static void main(String[] args) {
		System.out.println(new Burrito(NOT));
		System.out.println(new Burrito(MEDIUM));
		System.out.println(new Burrito(HOT));
	}
}