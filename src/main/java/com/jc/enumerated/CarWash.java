package com.jc.enumerated;

import java.util.EnumSet;

/**
 *  常量相关的方法——利用表驱动代码 和内部枚举 而不是内部类，代码显得更简洁
 * @author jevoncode
 *
 */
public class CarWash {
	public enum Cycle {
		UNDERBODY {
			void action() {
				System.out.println("Spraying the underbody");
			}
		},
		WHEELWASH {
			void action() {
				System.out.println("Washing the wheels");
			}
		},
		PREWASH {
			void action() {
				System.out.println("Loosening the dirt");
			}
		},
		BASIC {
			void action() {
				System.out.println("The basic wash");
			}
		},
		HOTWAX {
			void action() {
				System.out.println("Applying hot wax");
			}
		},
		RINSE {
			void action() {
				System.out.println("Rinsing");
			}
		},
		BLOWDRY {
			void action() {
				System.out.println("Blowing dry");
			}
		};
		abstract void action();
	}

	EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

	public void add(Cycle cycle) {
		cycles.add(cycle);
	}

	public void washCar() {
		for (Cycle c : cycles)
			c.action();
	}

	public String toString() {
		return cycles.toString();
	}

	public static void main(String[] args) {
		CarWash wash = new CarWash();
		System.out.println(wash);
		wash.washCar();
		// Order of addition is unimportant:
		wash.add(Cycle.BLOWDRY);
		wash.add(Cycle.BLOWDRY); // Duplicates ignored
		wash.add(Cycle.RINSE);
		wash.add(Cycle.HOTWAX);
		System.out.println(wash);
		wash.washCar();
	}
}