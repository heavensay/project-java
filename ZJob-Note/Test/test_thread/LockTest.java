package test_thread;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	ReentrantLock lock  = new ReentrantLock();
	
	public void f(){
//		lock.tryLock();
		lock.lock();
		System.out.println("The currect thread's name is "+Thread.currentThread().getName()+" I'm get lock");
		lock.unlock();
	}
	
	public static void main(String[] args){
		LockTest lockTest = new LockTest();
		Thread t1 = new Thread(new T(lockTest));
		Thread t2 = new Thread(new T(lockTest));
		t1.start();
		t2.start();
	}
}
class T implements Runnable{
	LockTest lockTest ;
	T(LockTest lockTest){
		this.lockTest = lockTest;
	}
	@Override
	public void run() {
		lockTest.f();
	}
	
}
