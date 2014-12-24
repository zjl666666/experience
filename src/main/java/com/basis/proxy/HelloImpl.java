package com.basis.proxy;

public class HelloImpl implements Hello {
	
	public void sayHello(String to) {
		System.out.println("Say hello to " + to);
	}

	public void print(String s) {
		System.out.println("print : " + s);
	}

}
