package com.search.util;

import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * 索引客户端
 * 较大的数据量可考虑使用StreamingUpdateSolrServer
 * 
 * @author zjl
 * 
 */
public class IndexClientUtil {

	/**
	 * 每次更新数据量相对比较少的使用
	 */
	private static HttpSolrServer server = null;
  
	
	
	private IndexClientUtil() {
		
	}

	/**
	 * 构造索引客户端
	 * @return  索引客户端
	 * @throws MalformedURLException
	 */
	public static SolrServer newInstance() throws MalformedURLException {
		if (server == null) {
			String url = "http://192.168.1.102:9090/newsReptileSearch";
			server = new HttpSolrServer(url);
			server.setSoTimeout(30000); // socket read tismeout
			server.setConnectionTimeout(1000);
			server.setDefaultMaxConnectionsPerHost(500);
			server.setMaxTotalConnections(1000);
			server.setFollowRedirects(false); // defaults to false
			server.setAllowCompression(true);
			server.setMaxRetries(1);
			// server.setParser(new XMLResponseParser());
		}
		return server;
	}
	
}
