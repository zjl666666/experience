/**
 * 
 */
package com.basis.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月20日 上午11:06:10
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<? extends Fruit> extendsList = new ArrayList<Fruit>();
		List<? extends Fruit> extendsList1 = new ArrayList<Apple>();
		// extendsList.add(new Apple()); //不能添加对象到list,因为不知道容器中具体的类型

		List<? super Fruit> superList = new ArrayList<Fruit>();
		// List<? super Fruit> superList1=new ArrayList<Apple>(); //不能创建包含Apple
		// 类型的容器对象，因为容器的下限是Fruit
		superList.add(new Apple());
		superList.add(new Fruit());
	}

	static <T> void superType(List<? super T> list, T arg) {
		list.add(arg);
		// T arg1=list.get(0);  //因为list持有的是任何的超类型,但传参过来的arg可能容器中类的超类，无法进行直接转换
	}
	
	static <T> void extendsType(List<? extends T> list, T arg) {
		//list.add(arg);
		
		T arg1=list.get(0);

	}

}
