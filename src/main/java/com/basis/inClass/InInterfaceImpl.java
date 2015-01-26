/**
 * 
 */
package com.basis.inClass;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月22日 下午3:13:00 
 */
public class InInterfaceImpl implements InInterface {

	/* (non-Javadoc)
	 * @see com.basis.inClass.InInterface#hello()
	 */
	@Override
	public void hello() {
          System.out.println("继承已实现了公共功能的接口");
	}

}
