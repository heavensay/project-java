package testhystrix;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHystrix {
 
	
	/**
	 * 
	 * result：注意CommandHelloWorld也是在一个新线程中执行，不在main线程中执行
	 */
	@Test
	public void simpleSynchronousTest1(){
		System.out.println("同步调用开始");
		System.out.println(Thread.currentThread().getName()+"  "+Thread.currentThread().hashCode());
		CommandHelloWorld chWorld = new CommandHelloWorld("Synchronous");
		System.out.println(chWorld.execute());
		System.out.println("同步调用结束");
	}
	
	/**
	 * 
	 * result:queue()异步执行测试
	 * @throws Exception
	 */
	@Test
	public void simpleAsynchronousTest2()  throws Exception {
		System.out.println("异步调用开始");
		System.out.println(Thread.currentThread().getName()+"  "+Thread.currentThread().hashCode());
		CommandHelloWorld chWorld = new CommandHelloWorld("Asynchronous");
//		System.out.println(chWorld.queue().get());   //get()方法会同步去获取结果，线程卡住
		System.out.println(chWorld.queue());
		System.out.println("异步调用结束");
		Thread.sleep(3000L);
		System.out.println("异步调用结束2");
	}
	
	/**
	 * 信号隔离调用测试
	 * result:commad跟调用方在同一个线程中
	 * @throws Exception
	 */
	@Test
	public void semaphoreInvokeTest3()  throws Exception {
		System.out.println("信号隔离调用开始");
		System.out.println(Thread.currentThread().getName()+"  "+Thread.currentThread().hashCode());
		CommandSemaphoreHelloWorld csWorld = new CommandSemaphoreHelloWorld("Semaphore");
		System.out.println(csWorld.execute());
		System.out.println("信号隔离调用结束");
	}	
	
	/**
	 * 测试hystrix线程池调用满之后，等待队列也满之后，服务拒绝
	 * 
	 * note：cmd执行时间2秒，超时设置为：10秒；
	 */
	@Test
	public void threadPoolTest4() throws Exception{
		System.out.println("test begin");
		/**
		 * 调整请求数量，线程数量，队列数量来查看效果
		 */
		for (int i = 0; i < 4; i++) {
			/**
			 *线程池：2，等待队列：1
			 *前面2个在执行，第3个在队列中等待；第四个由于超出等待队列数量，直接执行fallback
			 * 可以调整queueSize看看效果
			 */
			CommandDemo basicPoolCmd = new CommandDemo(String.valueOf(i), 2, 1);
			basicPoolCmd.queue();
		}
		
		Thread.sleep(30000L);
	}
}
