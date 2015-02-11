package com.mq.activemq.producer;
import java.util.List;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mq.activemq.domain.ChannelSubBehavior;
/**
 * 用户订阅行为记录消息发送
 * @author zjl
 *
 */
@Service
@Transactional(isolation =Isolation.READ_UNCOMMITTED)
public class ChannelSubBehaviorMessageProducer {
	private static Logger logger = LoggerFactory.getLogger(ChannelSubBehaviorMessageProducer.class);
	/**
	 * JMS模板
	 */
	@Autowired
	private JmsTemplate jmsTemplate;
	/**
	 * 用户行为队列
	 */
	@Autowired
	private Destination channelSubBehaviorQueue;

	
	public void sendMessage(List<ChannelSubBehavior> channelSubBehaviors) {
		try{
			jmsTemplate.convertAndSend(this.channelSubBehaviorQueue, channelSubBehaviors);
		}catch(Exception e){
			logger.error("发送订阅行为记录消息：sendMessage",e);
		}
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public void setNotifyQueue(Destination notifyQueue) {
		this.channelSubBehaviorQueue = notifyQueue;
	}
}
