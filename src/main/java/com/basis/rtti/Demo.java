/**
 * 
 */
package com.basis.rtti;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月23日 下午2:52:59 
 */
public class Demo {

	private static Pattern p=Pattern.compile("\\.");
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Class<?> c=Class.forName("com.basis.classloader.NetworkClassLoader");
		
		 Method[]  method=c.getMethods();
		 
		 
		 System.out.println(method.toString());
		 System.out.println(p.matcher(method.toString()).replaceAll(""));
		 
       
		
	}

}
