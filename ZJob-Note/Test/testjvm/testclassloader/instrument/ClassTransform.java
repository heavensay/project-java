package testjvm.testclassloader.instrument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
 
public  class  ClassTransform  implements ClassFileTransformer {
	
	public final static String transformedClass = "testjvm/testclassloader/instrument/Bean1.class";
	
    private  Instrumentation  inst;
    protected  ClassTransform(Instrumentation  inst){
       this.inst=inst;
    }
    
    
    /**
     * 此方法在redefineClasses时或者初次加载时会被调用，也就是说在class被再次加载时会被调用，
     * 并且我们通过此方法可以动态修改class字节码，实现类似代理之类的功能，具体方法可使用ASM或者javasist，
     * 如果对字节码很熟悉的话可以直接修改字节码。
     */
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
	     try {
	    	 System.out.println(className+"--"+loader);
			if (className.endsWith("Bean1.class")) {
				return getClassBytes(className);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    
	public static byte[] getClassBytes(String classname) throws Exception {
		File file = new File("D:/Ecpworkspace/ZJob-Note/bin/testjvm/testclassloader/instrument/Bean1.class");
		InputStream is = new FileInputStream(file);
		if (is == null)
			return null;
		byte[] bt = new byte[is.available()];
		is.read(bt);
		is.close();
		return bt;
	}
}