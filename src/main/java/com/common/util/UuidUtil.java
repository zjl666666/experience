/**
 * 
 */
package com.common.util;

import java.util.UUID;

/**
 * @author zjl
 * 生成32位uuid 工具类
 */
public abstract class UuidUtil {

	/**
	 * 生成唯一的id
	 * @description 	
	 * @return
	 * zjl  2012-11-20上午9:54:18
	 */
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
}
