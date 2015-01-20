package com.basis.generic.selfBound;

/**
 * 自限定 泛型
 * 
 * 
 * @Description: 使用者这个类的其他类必须是这个类的子类或者它自己
 * @author zjl
 * @date 2015年1月20日 下午3:20:18
 * @param <T>
 */
public class SelfBound<T extends SelfBound<T>> {

	T element;

	public T getElement() {
		return element;
	}

	public SelfBound<T> setElement(T element) {
		this.element = element;
		return this;
	}

}
