
package com.ssh.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 读取属性文件数据
 * @author zjl
 *
 */
public class MongodbPropertiesUtil {


    private static String FILE_NAME = "/properties/mongodb.properties";
    
    private static Properties properties;

    
    static {
        properties = getProperties(FILE_NAME);
    }

    public static void initEnvironment() {
        properties = getProperties(FILE_NAME);
    }

    private MongodbPropertiesUtil() {
    }

  
    public static Properties getProperties(String fileName) {
        Properties result = new Properties();
        try {
            result.load(MongodbPropertiesUtil.class.getResourceAsStream(fileName));
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
     * @param name 属性名
     * @return
     */
    public static String getProperty(String name) {
        return properties.getProperty(name);
    }

	public static void main(String args[]){
		String trt = MongodbPropertiesUtil.getProperty("taskName");
		System.out.println(trt);
	}
	
}
