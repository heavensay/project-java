package testdatabase.memcached;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * 使用XMemcached.jar包
 * memcached服务没开，客户端set,get操作会抛出异常
 */
public class TestXMemcached {
	
	MemcachedClient memcachedClient = null;

	@Before
	public void before() throws Exception{
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
					AddrUtil.getAddresses ("localhost:11211"));
		memcachedClient = builder.build();
	}
	
	@Test
	public void test1getAndsetSimple() throws TimeoutException, InterruptedException, MemcachedException{
		String key1 = "key-a";
		String value1 = "value-a";
		memcachedClient.set(key1, 0, value1);
		
		Object v = memcachedClient.get(key1);
		System.out.println(v);
	}
	
	@Test
	public void test2getAndsetObject() throws TimeoutException, InterruptedException, MemcachedException{
		String key1 = "key-a";
		Date date = new Date(System.currentTimeMillis());
		memcachedClient.set(key1, 0, date);
		
		Object v = memcachedClient.get(key1);
		System.out.println(v);
	}
	
	@Test
	public void test3Serialize() throws TimeoutException, InterruptedException, MemcachedException{
		String key1 = "key-a";
		Object item = new Object();
		memcachedClient.set(key1,0, item);
		
		Object v = memcachedClient.get(key1);
		System.out.println(v);
	}
}
