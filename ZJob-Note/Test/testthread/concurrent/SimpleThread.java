package testthread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.TestCase;

public class SimpleThread extends TestCase{
	public void test01(){
		ExecutorService es = Executors.newFixedThreadPool(3);
		es.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("aaaa");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("after sleep");
			}
		});
		es.shutdown();
	}
	
	String syn  =  "syn";
	
	public void test2(){
		Thread t1 = new Thread(new ThreadD(syn));
		t1.start();
		synchronized(syn){
			System.out.println("main ..... syn.."+syn);
		}
	}
	
}

class ThreadD implements Runnable{

	String syn = null;
	public ThreadD(String syn){
		this.syn = syn;
	}
	
	@Override
	public void run() {
		synchronized(getSyn()){
			System.out.println("ThreadA .... in..."+syn);
		}
	}

	public String getSyn() {
		return syn;
	}
	
}