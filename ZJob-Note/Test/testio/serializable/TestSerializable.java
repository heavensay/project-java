package testio.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class TestSerializable extends TestCase{
	
	public void test1() throws IOException, ClassNotFoundException{
		Bird bird = new Bird(1,"first bird");
		
		System.out.println(bird);
		Map map = new HashMap();
//		File file = new File("bird.ser");
		File file = new File("map.ser");
		if(!file.exists()){
			//file.mkdir();
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(bird);
		oos.writeObject(map);
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object o = ois.readObject();
		System.out.println(o);
		System.out.println(file.getAbsolutePath());
		ois.close();
		fis.close();
	}
	
	public void test9() throws IOException{
		System.out.println(new File("").getAbsolutePath());
		System.out.println(Bird.class.getResource(""));
		System.out.println(new File("").getCanonicalPath());
	}
}
