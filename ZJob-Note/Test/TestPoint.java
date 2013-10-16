import junit.framework.TestCase;

public class TestPoint extends TestCase {
	/**
	 * 测试 多线程中跑出异常如何处理 the result : the main thread don't catch the sub thread
	 * throw exception
	 */
	public void test1() {
		try {
			T t = new T();
			t.start();
			System.out.println("the main ");
		} catch (Exception e) {
			System.out.println("the mian thread catch the exception:"
					+ e.getMessage());
		}
	}
}

class T extends Thread {
	@Override
	public void run() {
		System.out.println("  thread :" + getName());
		throw new RuntimeException("aasdf");
	}
}
