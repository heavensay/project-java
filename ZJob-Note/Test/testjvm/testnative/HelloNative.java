package testjvm.testnative;

public class HelloNative {
	
	static{
		System.loadLibrary("hello");
	}
	/**
	 * 由c语言实现此功能
	 */
	public native static void nativeHello();
	
	public void sayHello(){
		nativeHello();
	}
}
