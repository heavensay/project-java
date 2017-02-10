package testthread.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestTimeUnit {
	
	@Test
	public void test1(){
		Long time = 120L;
		//time参数单位为分
		Long t1 = TimeUnit.MINUTES.toMillis(time);
		Long t2 = TimeUnit.MINUTES.toMinutes(time);
		Long t3 = TimeUnit.MINUTES.toHours(time);
		System.out.println("***TimeUnit.MINUTES***");
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		
		System.out.println("***TimeUnit.SECONDS***");
		//time参数单位为秒
		Long t4 = TimeUnit.SECONDS.toMillis(time);
		Long t5 = TimeUnit.SECONDS.toMinutes(time);
		Long t6 = TimeUnit.SECONDS.toHours(time);
		System.out.println(t4);
		System.out.println(t5);
		System.out.println(t6);
	}
	
	/**
	 * test timedWait();
	 * @throws InterruptedException
	 */
	@Test
	public void test2() throws InterruptedException{
		int count = 3;
		while(count-->0){
			synchronized (this) {
				try {
					Long t1 = System.currentTimeMillis();
					TimeUnit.SECONDS.timedWait(this, 3);
					Long t2 = System.currentTimeMillis();
					System.out.println(TimeUnit.MILLISECONDS.toSeconds(t2 - t1)
							+ "秒过去......");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}
		
		
		
	}
	
}
