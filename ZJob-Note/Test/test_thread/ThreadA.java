package test_thread;

/**
 * 测试TimeUnit类的用法
 * @author banana
 *
 */
class ThreadA {
	public static void main(String[] args) {
		int a  = 5; 
		ThreadB b = new ThreadB();
		b.start();
		System.out.println("b is start....");
//		ThreadC c = new ThreadC();
//		c.start();
		
		// 括号里的b是什么意思,起什么作用?  
		//调用一个Object的wait与notify/notifyAll的时候，必须保证调用代码对该Object是同步的，
		//也就是说必须在作用等同于synchronized(obj){......}的内部才能够去调用obj的wait与notify/notifyAll三个方法，否则就会报错：
		//  java.lang.IllegalMonitorStateException:current thread not owner
		synchronized (b)
		{
			try {
				System.out.println("Waiting for b to complete...");
				// 可以试试先运行b.notify,再运行b.wait();
				// 这一句是什么意思，究竟让谁wait?   e.g本线程wait，b线程可以继续运行   
				b.wait();
				System.out.println("Completed.Now back to main thread");
			} catch (InterruptedException e) {
			}
		}
		System.out.println(" Total is :" + b.total);
	}
}

class ThreadB extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			System.out.println("ThreadB is running..");
			for (int i = 0; i < 100; i++) {
				total += i;
				System.out.println("b total is " + total);
			}
			notify();
		}
	}
}

class ThreadC extends Thread {
	int total;

	public void run() {
			System.out.println("ThreadC is running..");
			for (int i = 0; i < 100; i++) {
				total += i;
				System.out.println("c: total is " + total);
			}
//			notify();
	}
}