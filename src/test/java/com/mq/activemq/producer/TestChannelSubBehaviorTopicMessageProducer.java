package com.mq.activemq.producer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mq.activemq.domain.ChannelSubBehavior;

//指定测试用例的运行器 这里是指定了Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext-mq-activemq.xml" })
public class TestChannelSubBehaviorTopicMessageProducer {
	
	@Resource(name = "channelSubBehaviorTopicMessageProducer")
	private ChannelSubBehaviorTopicMessageProducer channelSubBehaviorTopicMessageProducer;
	
	
	@Test
	public void testSendMessage() throws Exception{
		
//		while(true){
			ChannelSubBehavior channelSubBehavior=new ChannelSubBehavior();
			channelSubBehavior.setChannelType(1);
			channelSubBehavior.setId("1");
			
//			ChannelSubBehavior channelSubBehavior1=new ChannelSubBehavior();
//			channelSubBehavior1.setChannelType(2);
//			channelSubBehavior1.setId("2");
			
			List<ChannelSubBehavior> channelSubBehaviors=new ArrayList<ChannelSubBehavior>();
			channelSubBehaviors.add(channelSubBehavior);
//			channelSubBehaviors.add(channelSubBehavior1);
			
			channelSubBehaviorTopicMessageProducer.sendMessage(channelSubBehaviors);
		    
			Thread.sleep(100000L);
//		}
		
	}
}
