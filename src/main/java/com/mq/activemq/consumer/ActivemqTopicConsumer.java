package com.mq.activemq.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivemqTopicConsumer {
	private static Logger logger = LoggerFactory.getLogger(ActivemqTopicConsumer.class);

	private final String address = "tcp://192.168.20.126:61616";

	private final String topicName = "t.channel.sub.behavior.phone2";
	
	private final String clientID="activemq_topic_1";

	// ConnectionFactory ：连接工厂，JMS 用它创建连接
	ConnectionFactory connectionFactory;
	// Connection ：JMS 客户端到JMS Provider 的连接
	Connection connection = null;
	// Session： 一个发送或接收消息的线程
	Session session;
	// Destination ：消息的目的地;消息发送给谁.
	Topic destination;
	// 消费者，消息接收者
	MessageConsumer consumer;

	{
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, address);
	}

	public String receiveMessage() {
		 String result=null;

		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			connection.setClientID(clientID);
			// 启动
			connection.start();
			// 获取操作连接,第一个参数表示是否开启事务，第二个是签收模式
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createTopic(topicName);  
			TopicSubscriber  topicSubscriber= session.createDurableSubscriber(destination, "activemq_test"); 
			
			//阻塞的方式获取
//			while (true) {
//				// 设置接收者接收消息的时间，为了便于测试，这里谁定为100s
//				TextMessage message = (TextMessage) consumer.receive(100000);
//				if (null != message) {
//					result = message.getText();
//					logger.debug("Received message: " + message.getText());
//				} else {
//					break;
//				}
//			}
			
			//监听的方式获取
			topicSubscriber.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage tm = (TextMessage) message;
					try {
						logger.debug("Received message: " + tm.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			Thread.sleep(500000l);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}

		return result;
	}

}
