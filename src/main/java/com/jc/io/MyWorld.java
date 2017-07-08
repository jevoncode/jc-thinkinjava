package com.jc.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * AB关联、BC关联，在同一个流（同一个输出流或同一个输入流）中序列化，B的实例是同一个。
 * 其实这就是  深度复制 的一个例子，采用不同的流来复制对象
 * @author jevoncode
 *
 */
class House implements Serializable {
}

class Animal implements Serializable {
	private String name;
	private House preferredHouse;

	Animal(String nm, House h) {
		name = nm;
		preferredHouse = h;
	}

	public String toString() {
		return name + "[" + super.toString() + "], " + preferredHouse + "\n";
	}
}

public class MyWorld {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		House house = new House();
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("Bosco the dog", house));
		animals.add(new Animal("Ralph the hamster", house));
		animals.add(new Animal("Molly the cat", house));
		System.out.println("animals: " + animals);
		ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
		ObjectOutputStream o1 = new ObjectOutputStream(buf1);
		o1.writeObject(animals);
		o1.writeObject(animals); // Write a 2nd set
		// Write to a different stream:
		ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
		ObjectOutputStream o2 = new ObjectOutputStream(buf2);
		o2.writeObject(animals);
		// Now get them back:
		ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
		ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
		List animals1 = (List) in1.readObject(), animals2 = (List) in1.readObject(), animals3 = (List) in2.readObject();
		System.out.println("animals1: " + animals1);
		System.out.println("animals2: " + animals2);
		System.out.println("animals3: " + animals3);
	}
}