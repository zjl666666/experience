package com.basis.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class NetworkClassLoader extends URLClassLoader {

	public NetworkClassLoader(URL[] urls) {
		super(urls);
	}
	
	

}
