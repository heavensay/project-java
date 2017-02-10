package testthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试守护进程、用户进程区别(thread daemon测试)
 * 
 * 
 * result：只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；
 *         只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
 * @author admin
 *
 */
public class DaemonThreadTest {
	
	private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);
	
	public static void main(String[] args){
		DaemonThreadTest daemonThreadTest = new DaemonThreadTest();
		daemonThreadTest.deamonThreadTest();
	}
	
	/**
	 * SimpleTask只输出了sleep:0，jvm就退出了
	 */
	public void deamonThreadTest(){
		Thread t1 = new Thread(new SimpleTask());
		t1.setDaemon(true);
		logger.debug(" user thread start");
		t1.start();
		logger.debug(" main thread process end ");
	}
	
	/**
	 * SimpleTask执行完成，jvm退出 
	 * 输出内容完整sleep:0->sleep:4
	 */
	public void userThreadTest(){
		Thread t1 = new Thread(new SimpleTask());
		t1.setDaemon(false);
		logger.debug(" user thread start");
		t1.start();
		logger.debug(" main thread process end ");
	}
}

/**
 *
 */
class SimpleTask implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(SimpleTask.class);
	
	@Override
	public void run() {
		logger.debug(" user thread isdaemon:" +Thread.currentThread().isDaemon());
		for (int i = 0; i < 5; i++) {
			try {
				logger.debug("sleep:"+i);
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				logger.error(null,e);
			}
		}
	}
}