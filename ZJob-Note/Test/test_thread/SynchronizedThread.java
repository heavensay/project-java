package test_thread;

public class SynchronizedThread implements Runnable{

	private String s = "syn";
	
	public synchronized void f1(){
		System.out.println("f1   .."+s);
		f2();
	}
	
	public synchronized void f2(){
		System.out.println("f2  .. "+s);
	}
	public void f3(){
		System.out.println("f3   .."+s);
	}
	
	@Override
	public void run() {
		f1();
	}
	
	
	public static void main(String[] args){
		SynchronizedThread sy = new SynchronizedThread();
		new Thread(sy).start();
		sy.f3();
	}
}
