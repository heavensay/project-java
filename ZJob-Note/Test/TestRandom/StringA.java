package TestRandom;

public class StringA implements Runnable {
	String a = "abc";

	@Override
	public void run() {
		synchronized (a) {
			System.out.println("StringA synchronized");
		}
	}
}
