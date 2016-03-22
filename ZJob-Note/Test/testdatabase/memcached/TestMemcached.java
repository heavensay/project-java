package testdatabase.memcached;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;


/**
 *  Memcached-Java-Client.jar
 *  即使memcached服务没开，客户端set,get操作不会抛出异常
 * 
 */
public class TestMemcached {
	
	public static String hosts = "127.0.0.1:11211";
	public static MemCachedClient[] mccArr=new MemCachedClient[hosts.split(";").length];

	@Before
	public void before() throws Exception {
		// 服务器列表和其权重
		String[] serversArr = hosts.split(";");
		for (int i = 0; i < mccArr.length; i++) {
			MemCachedClient mcc = new MemCachedClient(i + "MemCachedClient");
			mccArr[i] = mcc;
			SockIOPool pool = SockIOPool.getInstance(i + "MemCachedClient");
			String[] servers = { serversArr[i] };
			Integer[] weights = { 3 };
			pool.setServers(servers);
			pool.setWeights(weights);
			pool.setInitConn(20);
			pool.setMinConn(20);
			pool.setMaxConn(500);
			pool.setMaxIdle(1000 * 60 * 60 * 2);
			// 设置主线程的睡眠时间
			pool.setMaintSleep(30);
			// 设置TCP的参数，连接超时等
			pool.setNagle(false);
			pool.setSocketTO(3000);
			pool.setSocketConnectTO(1000);
			// 初始化连接池
			pool.initialize();
		}
	}

	@Test
	public void test1getAndsetSimple(){
		String key1 = "key-a";
		String value1 = "value-a";
//		String value1 = null;
		getMemCachedClient().set(key1, value1);
		
		Object v = getMemCachedClient().get(key1);
		System.out.println(v);
	}
	
	@Test
	public void test2getAndsetObject(){
		String key1 = "key-a";
		Date date = new Date(System.currentTimeMillis());
		getMemCachedClient().set(key1, date);
		
		Object v = getMemCachedClient().get(key1);
		System.out.println(v);
	}

	@Test
	public void test3Serialize(){
		String key1 = "key-a";
		Object item = new Object();
		getMemCachedClient().set(key1, item);
		
		Object v = getMemCachedClient().get(key1);
		System.out.println(v);
	}
	
	private MemCachedClient getMemCachedClient(){
		return mccArr[0];
	}
}
