package com.jc.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 展示编译期智商的时候，之所以不能调用add方法，是因为
 * @author jevoncode
 */
public class GenericsAndCovariance {
	public static void main(String[] args) {
		// Wildcards allow covariance:
		List<? extends Fruit> flist = new ArrayList<Apple>();
		// Compile Error: can’t add any type of object:
		// flist.add(new Apple());
		// flist.add(new Fruit());
		// flist.add(new Object());
		flist.add(null); // Legal but uninteresting
		// We know that it returns at least Fruit:
		Fruit f = flist.get(0);
		
		flist.contains(new Apple()); // Argument is ‘Object’
		flist.indexOf(new Apple()); // Argument is ‘Object’
	}
}