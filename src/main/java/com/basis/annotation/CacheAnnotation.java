package com.basis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//定义成runtime将保存到java虚拟机运行时
@Target(ElementType.FIELD) //说明这个注解可以注解在基本所有的位置
@Inherited // 继承性如果有一个类使用了Inherited 注解的的注解，那么其子类将自动具有该注解
public @interface CacheAnnotation {
   
	String value();
	
	String key();
	
}
