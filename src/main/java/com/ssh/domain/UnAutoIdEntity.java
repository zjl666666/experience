/**
 * 
 */
package com.ssh.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Filename:    secretary→com.qoocc.admin.common.domain→UnAutoIdEntity.java
 * Description:  非自动递增主键 id
 * Copyright:   Copyright (c)2011 
 * Company:     qoocc 
 * @author:    	congge
 * @version:    1.0  
 * Create at:  2011-10-31下午03:56:36
 *  
 * Modification History:  
 * Date                      Author           Version     Description  
 * ------------------------------------------------------------------  
 * 2011-10-31下午03:56:36	       congge        1.0        1.0 Version  
 */
@MappedSuperclass
public class UnAutoIdEntity {
	/**
	 * 主键
	 */
	@Id
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
