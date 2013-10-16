package testjvm.testclassloader;

public class TestClassUnLoad {

	public static void main(String[] args) throws Exception {
		SimpleURLClassLoader loader = new SimpleURLClassLoader();
		// 用自定义的加载器加载A
		Class clazzA = loader.load("testjvm.testclassloader.A");
		Object a = clazzA.newInstance();
		// 清除相关引用
		a = null;
		clazzA = null;
		loader = null;
		// 执行一次gc垃圾回收
		System.gc();
		System.out.println("GC over");
	}
}
