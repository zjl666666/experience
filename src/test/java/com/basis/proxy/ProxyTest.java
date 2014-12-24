package com.basis.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxyTest {

	@Test
	public void testProxy() {
		HelloImpl impl = new HelloImpl();
		LogHandler handler = new LogHandler(impl);
		// 这里把handler与impl新生成的代理类相关联
		Hello hello = (Hello) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), handler);

		// 这里无论访问哪个方法，都是会把请求转发到handler.invoke
		hello.print("All the test");
//		hello.sayHello("Denny");
	}
}
