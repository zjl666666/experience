package com.ssh.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 统一定义id的entity基类.
 * 
 * @author zjl
 * @date 2011-6-8
 */
//JPA Entity基类的标识
@MappedSuperclass
public class IdEntity {

	/**
	 * 主键
	 */
	protected String id;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "nsUuid")    
    @GenericGenerator(name = "nsUuid", strategy = "uuid")   
    @Column(name = "id", unique = true, nullable = false) 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
