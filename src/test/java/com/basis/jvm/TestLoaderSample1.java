package com.basis.jvm;

public class TestLoaderSample1 {

	public static void main(String[] args) {
		Class c;
		ClassLoader cl;
		cl = ClassLoader.getSystemClassLoader();
		System.out.println(cl);
		while (cl != null) {
			cl = cl.getParent();
			System.out.println(cl);
		}
		try {
			c = Class.forName("com.basis.jvm.TestLoaderSample1");
			cl = c.getClassLoader();
			System.out.println(" LoaderSample1's loader is  " + cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
