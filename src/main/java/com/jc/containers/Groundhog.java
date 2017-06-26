package com.jc.containers;

/**
 * 没实现hashCode()和equals()
 * @author jevoncode
 *
 */
public class Groundhog {
	protected int number;

	public Groundhog(int n) {
		number = n;
	}

	public String toString() {
		return "Groundhog #" + number;
	}
}