package com.mq.activemq.consumer;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mq.activemq.domain.ChannelSubBehavior;

/**
 * 用户订阅行为记录消息监听器
 * 
 * @author zjl
 * 
 */
@Service
@Transactional
public class ChannelSubBehaviorMessageListener implements MessageListener {
	private static Logger logger = LoggerFactory.getLogger(ChannelSubBehaviorMessageListener.class);

	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = ((ObjectMessage) message);
			List<ChannelSubBehavior> channelSubBehaviors = (List<ChannelSubBehavior>) objectMessage.getObject();
			if (channelSubBehaviors != null && channelSubBehaviors.size()>0 ) {
				 for (int i = 0; i < channelSubBehaviors.size(); i++) {
					 logger.debug("channelSubBehaviors.get("+i+").getId()==="+channelSubBehaviors.get(i).getId());
				 }
			}
		} catch (JMSException e) {
			logger.error("从JMS消息中转化用户订阅信息出现错误", e);
		}
	}

}
