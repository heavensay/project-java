package testthread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class FutureTaskTest {
	    static FutureTask<Long> future = new FutureTask(new Callable<Long>(){
	        public Long call(){
	        	Long execTime = 10000L;
	        	//执行耗时任务
	        	
	        	System.out.println(" the task is begin");
	        	//模拟任务需要执行时间
	        	try {
					Thread.sleep(execTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	System.out.println(" the task is end");
	        	//返回执行结果
	        	return execTime;
	        }
	    });
	    
	    @Test
	    public void getResultTest(){
	    	new Thread(future).start();
//	    	Long result = future.get();
	    	Long result = null;
			try {
				result = future.get(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				future.cancel(true);
			}
	    	System.out.println(result);
	    }
	    
}
