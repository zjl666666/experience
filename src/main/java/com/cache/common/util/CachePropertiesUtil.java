package com.cache.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * 
 * @Description: 缓存配置常量读取工具类
 * @author zjl
 * @date 2014年8月26日 下午4:38:42
 */
public class CachePropertiesUtil {
	// 文件名
	private static String FILE_NAME = "/properties/cache-config.properties";

	private static Properties properties;

	static {
		properties = getProperties(FILE_NAME);
	}

	public static void initEnvironment() {
		properties = getProperties(FILE_NAME);
	}

	private CachePropertiesUtil() {
	}

	public static Properties getProperties(String fileName) {
		Properties result = new Properties();
		try {
			result.load(CachePropertiesUtil.class.getResourceAsStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取Properties
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * 获取字符串类型的属性
	 * 
	 * @param name
	 *            属性名
	 * @return
	 */
	public static String getProperty(String name) {
		return properties.getProperty(name);
	}
	
}
