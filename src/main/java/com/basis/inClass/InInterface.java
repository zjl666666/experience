/**
 * 
 */
package com.basis.inClass;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月22日 下午3:10:52
 */
public interface InInterface {
	void hello();

	class InInterfaceImpl implements InInterface {
		@Override
		public  void hello() {
			System.out.println("接口中的内部静态类，并实现接口本身，对继承接口的所有其他类实现公共的功能很便利");
		}

		
		public static void helloL() {
			System.out.println("接口中的内部静态类，并实现接口本身，对继承接口的所有其他类实现公共的功能很便利");
		}
	}
}
