package com.mq.activemq.domain;
import java.io.Serializable;
import java.util.Date;
/**
 * 用户行为
 * @date 2014-06-03
 */
@SuppressWarnings("serial")
public class ChannelSubBehavior implements Serializable{
	/**
	 * id
	 */
	private String id;
	/**
	 *  频道类型
	 */
	private Integer channelType;
	/**
	 *  系统类型
	 */
	private Integer sysType;
	
	/**
	 * ip信息
	 */
	private String ip;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 订阅类型
	 */
    private int subType;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}
	
	
}
