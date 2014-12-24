package com.basis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {

	private Object dele;

	public LogHandler(Object obj) {
		this.dele = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		doBefore();
		// 在这里完全可以把下面这句注释掉，而做一些其它的事情
		Object result = method.invoke(dele, args);
		after();
		return result;
	}

	private void doBefore() {
		System.out.println("before....");
	}

	private void after() {
		System.out.println("after....");
	}
}
