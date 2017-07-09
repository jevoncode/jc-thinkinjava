package com.jc.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
		// Read in the same order they were written:
		List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
		Line.deserializeStaticState(in);
		Circle.deserializeStaticState(in);
		Square.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>) in.readObject();
		System.out.println(shapes);
	}
}