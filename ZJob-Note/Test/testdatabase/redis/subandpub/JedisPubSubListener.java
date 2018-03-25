package testdatabase.redis.subandpub;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

public class JedisPubSubListener extends  JedisPubSub{
	
	Logger logger = LoggerFactory.getLogger(JedisPubSubListener.class);
	
	// 取得订阅的消息后的处理
	public void onMessage(String channel, String message) {
		// TODO:接收订阅频道消息后，业务处理逻辑
		logger.debug(channel + "=" + message);
	}

	// 初始化订阅时候的处理
	public void onSubscribe(String channel, int subscribedChannels) {
		 logger.debug(channel + "=" + subscribedChannels);
	}

	// 取消订阅时候的处理
	public void onUnsubscribe(String channel, int subscribedChannels) {
		 logger.debug(channel + "=" + subscribedChannels);
	}

	// 初始化按表达式的方式订阅时候的处理
	public void onPSubscribe(String pattern, int subscribedChannels) {
		 logger.debug(pattern + "=" + subscribedChannels);
	}

	// 取消按表达式的方式订阅时候的处理
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		 logger.debug(pattern + "=" + subscribedChannels);
	}

	// 取得按表达式的方式订阅的消息后的处理
	public void onPMessage(String pattern, String channel, String message) {
		logger.debug(pattern + "=" + channel + "=" + message);
	}
}
