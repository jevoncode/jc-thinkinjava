package com.jc.generic.util.coffee;

/**
 * 接口形式的泛型，这个生成器的接口是非常常用，需牢记
 * @author jevoncode
 *
 */
public interface Generator<T> {
	T next();
}
