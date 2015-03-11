package com.mq.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;



public class ProducerSample {
	public static void main(String[] args) {
		Properties properties = new Properties();
//		properties.put("zk.connect", "192.168.20.126:2181");
		properties.put("metadata.broker.list", "192.168.20.126:9092,192.168.20.126:9093");

		properties.put("serializer.class", "kafka.serializer.StringEncoder");

		ProducerConfig producerConfig = new ProducerConfig(properties);
		Producer<String, String> producer = new Producer<String, String>(producerConfig);

		// 构建消息体
		KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>("test-topic", "5000","test-message");
		
		producer.send(keyedMessage);

		producer.close();
	}
}
