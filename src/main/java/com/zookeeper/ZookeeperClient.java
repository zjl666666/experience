/**
 * 
 */
package com.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年2月2日 下午4:19:59
 */
public class ZookeeperClient {

	private static final Logger logger = LoggerFactory.getLogger(ZookeeperClient.class);

	private static ZooKeeper zk = null;

	private ZookeeperClient() {

	}

	private static class ZookeeperClientInner {
		private static final ZookeeperClient zookeeperClient = new ZookeeperClient();
	}

	public static ZookeeperClient newInstance() {
		return ZookeeperClientInner.zookeeperClient;
	}

	public static ZooKeeper getZookeeper() {
		if (zk != null) {
			return zk;
		}
		try {
			zk = new ZooKeeper("192.168.20.126:2181", 500000, new Watcher() {
				// 监控所有被触发的事件
				public void process(WatchedEvent event) {
					logger.debug("已经触发了" + event.getType() + "事件！");
				}
			});
		} catch (IOException e) {
			logger.error("创建zookeeper对象出现错误", e);
		}
		return zk;
	}
}
