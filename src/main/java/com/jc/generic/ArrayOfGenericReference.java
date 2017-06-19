package com.jc.generic;
/**
*
* 这个例子和 ArrayOfGeneric ，这两个例子有点混淆人，实际是想说明数组的类型有限制，跟泛型关系不大。
* 做了个论据在展示GenericArray和GenericArray2 为什么会运行时才报错。
 * 论据是数组在定义类型后，只能是类型及其子类才能放入数组中，而泛型可能会触犯到这个限制
* @author jevoncode
*/
class Generic<T> {
}

public class ArrayOfGenericReference {
	static Generic<Integer>[] gia;
}