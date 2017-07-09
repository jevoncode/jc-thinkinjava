package com.jc.enumerated;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import com.jc.io.util.OSExecute;

/**
 * 所有枚举都是继承java.lang.Enum的类，且由编译器给各个枚举添加values()和valuesof()方法
 * 通过反编译可以看出，枚举其实是一个final的类，所以枚举是不能被继承的。
 * @author jevoncode
 *
 */
enum Explore {
	HERE, THERE
}

public class Reflection {
	public static Set<String> analyze(Class<?> enumClass) {
		System.out.println("----- Analyzing " + enumClass + " -----");
		System.out.println("Interfaces:");
		for (Type t : enumClass.getGenericInterfaces())
			System.out.println(t);
		System.out.println("Base: " + enumClass.getSuperclass());
		System.out.println("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for (Method m : enumClass.getMethods())
			methods.add(m.getName());
		System.out.println(methods);
		return methods;
	}

	public static void main(String[] args) {
		Set<String> exploreMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		System.out.println("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
		System.out.print("Explore.removeAll(Enum): ");
		exploreMethods.removeAll(enumMethods);
		System.out.println(exploreMethods);
		// Decompile the code for the enum:
		OSExecute.command("javap Explore");
	}
}/* Output:
----- Analyzing class Explore -----
Interfaces:
Base: class java.lang.Enum
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, values, wait]
----- Analyzing class java.lang.Enum -----
Interfaces:
java.lang.Comparable<E>
interface java.io.Serializable
Base: class java.lang.Object
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, wait]
Explore.containsAll(Enum)? true
Explore.removeAll(Enum): [values]
Compiled from "Reflection.java"
final class Explore extends java.lang.Enum{
public static final Explore HERE;
public static final Explore THERE;
public static final Explore[] values();
public static Explore valueOf(java.lang.String);
static {};
}
*///:~