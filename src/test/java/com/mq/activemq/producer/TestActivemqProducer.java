/**
 * 
 */
package com.mq.activemq.producer;

import org.junit.Test;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年2月9日 下午5:31:40 
 */
public class TestActivemqProducer {

	@Test
	public void sendMessage(){
		String message="测试队列发送消息";
		ActivemqProducer producer=new ActivemqProducer();
		producer.sendMessage(message);
	}
}
