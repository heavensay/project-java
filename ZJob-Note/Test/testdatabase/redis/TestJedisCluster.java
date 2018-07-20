package testdatabase.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class TestJedisCluster {

    /**
     * redis集群连接查询简单测试
     * @throws Exception
     */
    @Test
    public void testSimple() throws Exception{
        HostAndPort hp1 = new HostAndPort("19.19.19.187",7000);
        HostAndPort hp2 = new HostAndPort("19.19.19.187",7001);
        HostAndPort hp3 = new HostAndPort("19.19.19.187",7002);
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(hp1);
        set.add(hp2);
        set.add(hp3);

        JedisCluster jedisCluster = new JedisCluster(set,10000, 1000,3 , new GenericObjectPoolConfig());
        String keys = "name";
        // 存数据
        jedisCluster.set(keys, "ths");
        // 取数据
        String value = jedisCluster.get(keys);
        System.out.println(value);
        // 删数据
        jedisCluster.del(keys);
        System.out.println(jedisCluster.get(keys));
    }

}
