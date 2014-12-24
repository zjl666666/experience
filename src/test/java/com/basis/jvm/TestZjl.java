/**
 * 
 */
package com.basis.jvm;

import org.junit.Test;

/**
 * @Description: 测试class对象
 * @author zjl
 * @date 2014年11月17日 下午4:41:42
 */
public class TestZjl {

	@Test
	public void testLoad() throws Exception {
		Class zjl = Class.forName("com.basis.jvm.Zjl");
		Class zjl1 = Class.forName("com.basis.jvm.Zjl");
		zjl.newInstance();
	}
}
