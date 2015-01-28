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
public class LRUdemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer,String> map=new LinkedHashMap<Integer,String>(2,0.75f,true);
		map.put(1, "test");
		map.put(2, "test");
		map.put(3, "test");
		map.put(4, "test");
		
		System.out.println(map);
		
		map.get(3);
		map.get(1);
		map.get(2);
		System.out.println(map);
		map.put(5, "test");
		map.put(6, "test");
		System.out.println(map);
		map.put(7, "test");
		System.out.println(map);
		
		System.out.println(map.entrySet().iterator().next().getKey());

		
		Map<Integer,String> lruMap=new LRULinkedHashMap<Integer,String>(2);
		lruMap.put(1, "test");
		lruMap.put(2, "test");
		lruMap.put(3, "test");
		System.out.println(lruMap);
		lruMap.get(1);
		System.out.println(lruMap);
	}

}
