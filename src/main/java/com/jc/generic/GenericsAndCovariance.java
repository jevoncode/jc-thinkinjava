package com.jc.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 虽然NonCovariantGenerics解决了CovariantArrays这例子的问题，但又引来新问题，编译不过咋整。
 * 于是这个GenericsAndCovariance例子增加通配符，解决了泛型容器可向上转型，但又引来新问题：
 * 不能调用add方法，在这个例子里，编译器认可Fruit的list，但不能给任何Fruit或及子类使用add方法，
 * 是怕把不是apple相关的给放进去了。在这里编译期智商可能有点低，一律拒绝，哪怕是apple对象也不给add了。
 * 之说以contains和indexof能够使用，是因为这两个方法不包含泛型。
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