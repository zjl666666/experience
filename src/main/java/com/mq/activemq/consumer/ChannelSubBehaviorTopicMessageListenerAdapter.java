package com.mq.activemq.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mq.activemq.domain.ChannelSubBehavior;

@Service
public class ChannelSubBehaviorTopicMessageListenerAdapter {
	private static Logger logger = LoggerFactory.getLogger(ChannelSubBehaviorTopicMessageListenerAdapter.class);

	public void receiveMessage(List<ChannelSubBehavior> channelSubBehaviors) {
		if (channelSubBehaviors != null && channelSubBehaviors.size() > 0) {
			for (int i = 0; i < channelSubBehaviors.size(); i++) {
				logger.debug("channelSubBehaviors.get(" + i + ").getId()===" + channelSubBehaviors.get(i).getId());
			}
		}
	}

}
