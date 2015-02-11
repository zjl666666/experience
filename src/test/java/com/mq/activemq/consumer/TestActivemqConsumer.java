package com.mq.activemq.consumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestActivemqConsumer {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Test
	public void testReceiveMessage(){
		ActivemqConsumer consumer=new ActivemqConsumer();
		String result=consumer.receiveMessage();
		
		logger.debug("result====="+result);
	}
}
