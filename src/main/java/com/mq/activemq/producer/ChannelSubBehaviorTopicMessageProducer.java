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
 * 用户订阅 行为记录消息发送pub/sub模式
 * 
 * @author zjl
 * 
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class ChannelSubBehaviorTopicMessageProducer {
	private static Logger logger = LoggerFactory.getLogger(ChannelSubBehaviorTopicMessageProducer.class);
	/**
	 * JMS模板
	 */
	@Autowired
	private JmsTemplate jmsTopicTemplate;
	/**
	 * 用户行为队列
	 */
	@Autowired
	private Destination channelSubBehaviorTopic;

	public void sendMessage(List<ChannelSubBehavior> channelSubBehaviors) {
		try {
			jmsTopicTemplate.convertAndSend(this.channelSubBehaviorTopic, channelSubBehaviors);
		} catch (Exception e) {
			logger.error("发送订阅行为记录消息：sendMessage", e);
		}
	}

}
