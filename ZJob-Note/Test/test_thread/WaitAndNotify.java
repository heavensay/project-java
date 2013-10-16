package test_thread;

public class WaitAndNotify {

	public static void main(String[] args) {
		Object lock = new Object();
		SourceThread t1 = new SourceThread(lock);
		SourceThread2 t2 = new SourceThread2(lock);
		t1.start();
		t2.start();
	}
}

class SourceThread extends Thread {
	private Object source;

	SourceThread(Object source) {
		this.source = source;
	}
	@Override
	public void run() {
		synchronized (source) {
			try {
				source.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		;
	}
}
class SourceThread2 extends Thread {
	private Object source;

	SourceThread2(Object source) {
		this.source = source;
	}
	public void run() {
		synchronized (source) {
			System.out.println(Thread.currentThread().getName());
//			source.notifyAll();
		}
	}
}