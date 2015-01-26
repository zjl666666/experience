/**
 * 
 */
package com.basis.inClass;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月22日 下午3:13:51 
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InInterfaceImpl inInterfaceImpl=new InInterfaceImpl();
		
		InInterface.InInterfaceImpl  inInterfaceInInterfaceImpl=new InInterface.InInterfaceImpl();
		inInterfaceInInterfaceImpl.hello();
		
		InInterface.InInterfaceImpl.helloL();
	}

}
