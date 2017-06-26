package com.jc.containers;

import java.util.Random;
/**
 * 跟Groundhog和Groundhog2配合展示hashcode的使用
 * @author jevoncode
 *
 */
public class Prediction {
	private static Random rand = new Random(47);
	private boolean shadow = rand.nextDouble() > 0.5;

	public String toString() {
		if (shadow)
			return "Six more weeks of Winter!";
		else
			return "Early Spring!";
	}
}