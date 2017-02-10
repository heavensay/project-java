package testthread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注意：junit在单元测试中，如果单元测试中产生多个线程，但是主线程结束之后，衍生出的子线程没有执行完就整个退出虚拟机。
 *       CountDownLatch 用于阻塞主线程，等子线程完成。等待时间>子线程执行时间
 * @author admin
 *
 */
public class ThreadTest {

	private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);
	
	/**
	 * @see  testthread.DaemonThreadTest
	 */
	@Test
	@Deprecated
	public void deamonThreadTest(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						logger.debug("sleep:"+i);
						Thread.sleep(3000L);
					} catch (InterruptedException e) {
						logger.error(null,e);
					}
				}
			}
		});
		t1.setDaemon(true);
		logger.debug(" user thread start");
		t1.start();
		logger.debug(" user thread isdaemon:" +t1.isDaemon());
		
		/* 
		 * junit在单元测试中，如果单元测试中产生多个线程，但是主线程结束之后，衍生出的子线程没有执行完就整个退出虚拟机。
		 * CountDownLatch 用于阻塞主线程，等子线程完成。等待时间>子线程执行时间
		 */
		CountDownLatch countDownLatch = new CountDownLatch(1);
		try {
			countDownLatch.await(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(null,e);
		}
		logger.debug(" main thread isdaemon:"+t1.isDaemon());
		logger.debug(" main thread process end ");
	}
	
}
