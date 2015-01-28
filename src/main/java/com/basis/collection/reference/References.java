/**
 * 
 */
package com.basis.collection.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.WeakHashMap;

/**
 * @Description: 各种引用的使用，引用的强度依次是:强引用--》软引用--》弱引用--》虚引用
 * 软引用--》弱引用--》虚引用这些引用当内存不够用的时候会被垃圾回收自动回收，
 * 软引用一般可以用作缓存
 * 弱引用可用做映射对象集，供程序其他地方使用，不影响垃圾回收
 * 虚引用一般用作垃圾回收前的清理工作或者标记工作
 * @author zjl
 * @date 2015年1月28日 上午10:11:32 
 */
public class References {

	public static ReferenceQueue<VeryBig> rq=new ReferenceQueue<VeryBig>(); 
	
	public static void checkRq(){
		Reference<? extends VeryBig> reference=rq.poll();
		if(reference!=null){
		    System.out.print("ReferenceQueue value==="+reference.get());	
		}
	}
	
	public  static void main(String args[]){
		int size=10;
		 
		LinkedList<SoftReference<VeryBig>> softRefereces=new LinkedList<SoftReference<VeryBig>>();
		for(int i=0;i<size;i++){
			SoftReference<VeryBig> sfVeryBig=new SoftReference<VeryBig>(new VeryBig("test softReference"+i),rq);
			softRefereces.add(sfVeryBig);
			checkRq();
		}
		
		LinkedList<WeakReference<VeryBig>> weakRefereces=new LinkedList<WeakReference<VeryBig>>();
		for(int i=0;i<size;i++){
			WeakReference<VeryBig> wfVeryBig=new WeakReference<VeryBig>(new VeryBig("test weakReference"+i),rq);
			weakRefereces.add(wfVeryBig);
			checkRq();
		}
		
		LinkedList<PhantomReference<VeryBig>> phantomRefereces=new LinkedList<PhantomReference<VeryBig>>();
		for(int i=0;i<size;i++){
			PhantomReference<VeryBig> pfVeryBig=new PhantomReference<VeryBig>(new VeryBig("test phantomReferece"+i),rq);
			phantomRefereces.add(pfVeryBig);
			checkRq();
		}
		
		WeakHashMap<VeryBig,String> weakHashMap=new WeakHashMap<VeryBig,String>();
		VeryBig  veryBigtest=new VeryBig("test weakHashMap1");
		VeryBig  veryBigtest1=new VeryBig("test weakHashMap2");
		weakHashMap.put(veryBigtest, "veryBigtest");
		weakHashMap.put(veryBigtest1, "veryBigtest1");
		
		System.gc();
	}
}
