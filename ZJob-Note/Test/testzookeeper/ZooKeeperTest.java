package testzookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class ZooKeeperTest {

	private static final String connectString = "192.168.137.10:2181,"
												+ "192.168.137.10:2182,"
												+ "192.168.137.10:2183";
	
	/**
	 * zookeeper简单试用例子
	 * 代码参考http://blog.csdn.net/huwei2003/article/details/49101269
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		ZooKeeper zk = new ZooKeeper(connectString, 300000, new Watcher() {

			public void process(WatchedEvent event) {
	            System.out.println("----------->");
	            System.out.println("path:" + event.getPath());
	            System.out.println("type:" + event.getType());
	            System.out.println("stat:" + event.getState());
	            System.out.println("<-----------");
			}
		});// 连接zk server
		String node = "/app1";
		Stat stat = zk.exists(node, false);// 检测/app1是否存在
		if (stat == null) {
			// 创建节点
			String createResult = zk.create(node, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
			System.out.println("zk.create:"+createResult);
		}
		// 获取节点的值
		byte[] b = zk.getData(node, false, stat);
		stat = zk.exists(node, true);
		System.out.println("zk.getData:"+new String(b));
		System.out.println("zk.stat:"+stat.toString());
		
		//修改节点值
		zk.setData(node,"test2".getBytes(),stat.getVersion());
		stat = zk.exists(node, false);
		System.out.println("zk.getData:"+new String(b));
		System.out.println("zk.stat:"+stat.toString());
		
		
		Thread.sleep(3000L);
		
		zk.close();
	}

}
