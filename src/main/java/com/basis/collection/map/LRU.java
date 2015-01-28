/**
 * 
 */
package com.basis.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月27日 上午11:29:39 
 */
public class LRU {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer,String> map=new LinkedHashMap<Integer,String>(6,0.75f,true);
		map.put(1, "test");
		map.put(2, "test");
		map.put(3, "test");
		map.put(4, "test");
		
		System.out.println(map);
		
		map.get(3);
		map.get(1);
		map.get(2);
		map.get(3);
		map.get(1);
		map.get(3);
		System.out.println(map);

	}

}
