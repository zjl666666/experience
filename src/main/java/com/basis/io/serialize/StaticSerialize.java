package com.basis.io.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * 
 * @Description: 测试static 字段序列化
 * @author zjl
 * @date 2015年2月10日 上午10:34:19
 */
public class StaticSerialize implements Serializable{

	public static String str="test";
	
	public static void main(String[] args) throws Exception, ClassNotFoundException {
//		StaticSerialize serialize=new StaticSerialize();
//		StaticSerialize.str="testSerialize";
//		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("D:\\StaticSerialize.out"));
//		o.writeObject(serialize);
//		o.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\StaticSerialize.out"));
		StaticSerialize serialize1 = (StaticSerialize) in.readObject();
		System.out.println(serialize1.str);
		
		
		// Now get it back:
//		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\StaticSerialize.out"));
//		Class<StaticSerialize> serialize1 = (Class) in.readObject();
//		System.out.println(serialize1.getField("str").get(serialize1.newInstance()));
	}
}
