package testjvm.testclassloader;

import org.junit.Test;

/**
 * 测试class路径相关
 * @author admin
 *
 */
public class TestClassCorrelationPath {
	
	@Test
	public void classPath(){
		// CustomURLClassLoader.class.getClassLoader().getr
		// URL url = new File(getCurrentDir()).toURI().toURL();
//		URL url = new File("D:/Ecpworkspace/ZJob-Note/bin/").toURI().toURL();
		System.out.println(" 得到工程存放clas文件路径: Thread.currentThread().getContextClassLoader().getResource(\"\").getPath() :"+Thread.currentThread().getContextClassLoader().getResource("").getPath());
		System.out.println("得到具体单个class文件: TestClassCorrelationPath.class.getResource(\"\") :"+TestClassCorrelationPath.class.getResource(""));
	}

	@Test
	public void projectPath(){
		System.out.println("得到工程路径调用System.getProperty(\"user.dir\"):"+System.getProperty("user.dir"));
	}
	
	/**
	 * class.getResource(...)跟classLoader.getResource(...)测试
	 * 跟取文件路径概念差不多，class.getResource(...)与classLoader.getResource(...)区别在于前者路径
	 * 定位到class所在的包，后者则是classLoader可以加在的classpath。
	 * 
	 */
	@Test
	public void getResourceDifference(){
		
		System.out.println(this.getClass().getClassLoader().getResource("testjvm/testclassloader/TestClassCorrelationPath.class"));
		System.out.println(this.getClass().getResource("testjvm/testclassloader/TestClassCorrelationPath.class"));
		System.out.println(this.getClass().getResource("TestClassCorrelationPath.class"));
		System.out.println("---------------");
		//this.class下面子包
		System.out.println(this.getClass().getResource("hot/A.class"));
		//跟this.class不同的包
		System.out.println(this.getClass().getResource("testjvm/testnative/D.class"));
		System.out.println(this.getClass().getResource("../testnative/D.class"));
		System.out.println(this.getClass().getClassLoader().getResource("testjvm/testclassloader/A.class"));
		
	}
}
