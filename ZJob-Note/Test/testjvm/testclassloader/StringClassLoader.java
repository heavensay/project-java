package testjvm.testclassloader;

import java.io.ByteArrayOutputStream;
import java.io.File;  
import java.io.FileInputStream;  
import java.lang.reflect.Method;  
import java.net.URL;  
import java.net.URLClassLoader;  
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
   
/** 
* 可以重新载入同名类的类加载器实现 
* 
  
* 放弃了双亲委派的加载链模式. 
* 需要外部维护重载后的类的成员变量状态. 
* 
* @author ken.wu 
* @mail ken.wug@gmail.com 
* 2007-9-28 下午01:37:43 
*/  
public class StringClassLoader extends URLClassLoader {  
   
    public StringClassLoader(URL[] urls) {  
        super (urls);  
    }  
   
    public StringClassLoader(URL[] urls, ClassLoader parent) {  
        super (urls, parent);  
    }  
   
	public Class load(String name) throws Exception {
		// First, check if the class has already been loaded
		Class c = findLoadedClass(name);
		if (c == null) {
//			c = findClass(name);
			File file = new File("D:/Ecpworkspace/ZJob-Note/bin/java/lang/String.class");
			FileInputStream fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			WritableByteChannel outC  = Channels.newChannel(baos);
			
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
            while (true) {  
                int i = fc.read(buffer);  
                if (i == 0 || i == -1) {  
                    break;  
                }  
                buffer.flip();  
                outC.write(buffer);  
                buffer.clear();  
            }  
            fis.close();
            byte[] b = baos.toByteArray();
			return defineClass(name, b, 0, b.length);
		}
		return c;
	}
	
}  
