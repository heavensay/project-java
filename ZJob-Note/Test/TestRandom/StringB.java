package TestRandom;

public class StringB implements Runnable {
	String b = "abc";

	@Override
	public void run() {
		synchronized (b) {
			System.out.println("StringB synchronized");
		}
	}
}
