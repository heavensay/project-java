package testdatabase.redis.subandpub;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import testdatabase.redis.RedisUtil;

public class RedisPublish {
	
	@Test
	public static void main(String[] args){
		Jedis jedis = RedisUtil.getJedis();
		jedis.publish("im", "hello"+System.currentTimeMillis());
	}

}
