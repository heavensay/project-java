package testdatabase.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import testio.serializable.util.ObjectsTranscoder;


/**
 * 在redis中测试Object的存取
 * @author banana
 *
 */
public class TestRedisObject {
	
	Logger log = Logger.getLogger(TestRedisObject.class);
	
	@Test
	public void test(){
		
		User user1 = new User("tom",18);
		
		ObjectsTranscoder<User> otc = new ObjectsTranscoder<User>();
		byte[] a1 = otc.serialize(user1);
		
		String key = "key1";
		byte[] k1 = key.getBytes();
		
		RedisUtil.getJedis().set(k1, a1);
//		RedisUtil.getJedis().set(key, "v_111");//会覆盖a1值
		
		User u1 = otc.deserialize(RedisUtil.getJedis().get(k1));
		log.debug("user:age="+u1.getAge()+" name="+u1.getName());
//		log.debug("key1-value:"+RedisUtil.getJedis().get(key));
	}

	@Test
	public void test2(){
		
		ArrayList list = new ArrayList();
		User user1 = new User("tom",18);
		User user2 = new User("jack",19);
		
		list.add(user1);
		list.add(user2);
		
		ObjectsTranscoder<ArrayList> otc = new ObjectsTranscoder<ArrayList>();
		
		byte[] a1 = otc.serialize(list);
		byte[] k1 = "k1".getBytes();
		
		RedisUtil.getJedis().set(k1, a1);
		
		List u1 = otc.deserialize(RedisUtil.getJedis().get(k1));
		log.debug("list.size="+list.size());
	}
	
	
	public static class User implements Serializable{
		
		private String name = null;
		private Integer age = null;
		
		public User(String name,int age){
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
	}
}




