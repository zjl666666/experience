/**
 * 
 */
package com.basis.array;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月22日 上午9:43:32 
 */
public class Demo {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] n={{1,2,3},{4,5,6}};
		System.out.println(n[0][2]);
		
		int[] i=new int[5];
		int[] j=new int[7];
		
		Arrays.fill(i, 5);
		Arrays.fill(j, 6);
		
		for(int a=0;a<j.length;a++){
			System.out.println(j[a]);
		}
		
		System.arraycopy(i, 0, j, 0, i.length);
		
		for(int a=0;a<j.length;a++){
			System.out.println(j[a]);
		}
		
	}

}
