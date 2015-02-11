/**
 * 
 */
package com.mq.activemq.producer;

import org.junit.Test;

import com.mq.activemq.consumer.ActivemqTopicConsumer;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年2月9日 下午5:31:40 
 */
public class TestActivemqTopicProducer {

	@Test
	public void sendMessage(){
		String message="测试主题发送消息";
		
		
		ActivemqTopicProducer producer=new ActivemqTopicProducer();
		producer.sendMessage(message);
		
//		ActivemqTopicConsumer consumer=new ActivemqTopicConsumer();
//		String result=consumer.receiveMessage();
		
		
		
	}
}
