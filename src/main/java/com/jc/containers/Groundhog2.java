package com.jc.containers;

/**
 * 这个equals写的很严谨
 * 1.自反性
 * 2.对称性
 * 3.传递性
 * 4.一致性
 * 5.对于任何不是null的x，x.equals(null)=false
 * @author jevoncode
 *
 */
public class Groundhog2 extends Groundhog {
	public Groundhog2(int n) {
		super(n);
	}

	public int hashCode() {
		return number;
	}

	public boolean equals(Object o) {
		return o instanceof Groundhog2 && (number == ((Groundhog2) o).number);
	}
}