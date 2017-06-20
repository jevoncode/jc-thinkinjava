package com.jc.generic.util.tuple;

import java.util.Date;

/**
 * 给一个类添加功能， 简单版
 * 里面没用到泛型，为啥跟泛型扯上关系的呢？是这样的，泛型的目的就是写一个更通用的代码。
 * 而给一堆类添加某个功能，也叫通用代码。不过aop会更厉害点而已。而且更重要的是
 * C++里的模板提供了给一堆类添加功能的功能，而模板又对应java的泛型，于是不得不提这个给类添加功能的场景。
 * 把类混在一起，以达到添加新的功能，更专业的叫法：混型(Mixins)
 * @author jevoncode
 *
 */
interface TimeStamped {
	long getStamp();
}

class TimeStampedImp implements TimeStamped {
	private final long timeStamp;

	public TimeStampedImp() {
		timeStamp = new Date().getTime();
	}

	public long getStamp() {
		return timeStamp;
	}
}

interface SerialNumbered {
	long getSerialNumber();
}

class SerialNumberedImp implements SerialNumbered {
	private static long counter = 1;
	private final long serialNumber = counter++;

	public long getSerialNumber() {
		return serialNumber;
	}
}

interface Basic {
	public void set(String val);

	public String get();
}

class BasicImp implements Basic {
	private String value;

	public void set(String val) {
		value = val;
	}

	public String get() {
		return value;
	}
}

class Mixin extends BasicImp implements TimeStamped, SerialNumbered {
	private TimeStamped timeStamp = new TimeStampedImp();
	private SerialNumbered serialNumber = new SerialNumberedImp();

	public long getStamp() {
		return timeStamp.getStamp();
	}

	public long getSerialNumber() {
		return serialNumber.getSerialNumber();
	}
}

public class Mixins {
	public static void main(String[] args) {
		Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
		mixin1.set("test string 1");
		mixin2.set("test string 2");
		System.out.println(mixin1.get() + " " + mixin1.getStamp() + " " + mixin1.getSerialNumber());
		System.out.println(mixin2.get() + " " + mixin2.getStamp() + " " + mixin2.getSerialNumber());
	}
}