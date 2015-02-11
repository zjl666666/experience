package com.mq.activemq.consumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestActivemqTopicConsumer {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Test
	public void testReceiveMessage(){
		ActivemqTopicConsumer consumer=new ActivemqTopicConsumer();
		String result=consumer.receiveMessage();
		
		logger.debug("result====="+result);
	}
	
	public static void main(String args[]){
		ActivemqTopicConsumer consumer=new ActivemqTopicConsumer();
		String result=consumer.receiveMessage();
	}
}
