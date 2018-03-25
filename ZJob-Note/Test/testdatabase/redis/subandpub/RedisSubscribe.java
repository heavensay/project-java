package testdatabase.redis.subandpub;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import testdatabase.redis.RedisUtil;

public class RedisSubscribe {

	
	/**
	 * 由于Jedis的subcribe操作是阻塞的
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception{
		Jedis jedis = RedisUtil.getJedis();
 		jedis.subscribe(new JedisPubSubListener(), "im");
	}

}

