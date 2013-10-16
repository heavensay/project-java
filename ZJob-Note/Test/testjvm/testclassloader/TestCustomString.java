package testjvm.testclassloader;

import java.io.File;
import java.net.URL;

public class TestCustomString {  
	public static void main(String args[]) throws Exception{ 
		
	    String b = new String(" cccc ");  
	   
	    System.out.printf("A classLoader is %s \n" , b.getClass().getClassLoader()+b);  
//	    b.f();
	    File file = new File("D:/Ecpworkspace/ZJob-Note/bin");
	    
//	    HotSwapClassLoader c1 = new HotSwapClassLoader( new URL[]{ new URL( "file:D:/Ecpworkspace/ZJob-Note/bin")} , a.getClass().getClassLoader());  
	    StringClassLoader c1 = new StringClassLoader( new URL[]{ file.toURL()} , b.getClass().getClassLoader());
	    
	    System.out.println(file.exists()+"-----"+c1.getURLs()[0].getPath());
	    Class clazz = c1.load("java.lang.String");
	    
	    Object aInstance = clazz.newInstance();  
	   
	    
//	    Method method1 = clazz.getMethod("setB", B.class);  
	   
	    System.out.printf(" reloaded A classLoader is %s \n", clazz.getClassLoader());
	}  
	}  
