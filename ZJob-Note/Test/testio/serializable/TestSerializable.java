package testio.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import testio.serializable.util.ObjectsTranscoder;

import junit.framework.TestCase;

public class TestSerializable extends TestCase{
	
	private static Logger logger = Logger.getLogger(TestSerializable.class);
	
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
	
	public void test2() throws IOException{
		System.out.println(new File("").getAbsolutePath());
		System.out.println(Bird.class.getResource(""));
		System.out.println(new File("").getCanonicalPath());
	}
	
	public void test3Object(){
		ObjectsTranscoder<Bean1> otc = new ObjectsTranscoder<Bean1>();
		Bean1 bean1 = new Bean1();
		bean1.setAge(18);
		bean1.setName("tom");
		
		byte[] bean1Bytes = otc.serialize(bean1);
		
		Bean1 bean_1 = otc.deserialize(bean1Bytes);
		System.out.println(bean1);
		logger.debug("before serialize bean:"+bean1+"\n" +
					 "after serialize bean:"+bean_1+" age="+bean_1.getAge()+" name="+bean_1.getName());
	}
	
	public void test4List(){
		ObjectsTranscoder<ArrayList> otc = new ObjectsTranscoder<ArrayList>();
		ArrayList list = new ArrayList();
		Bean1 bean1 = new Bean1();
		bean1.setAge(18);
		bean1.setName("tom");

		Bean1 bean2 = new Bean1();
		bean2.setAge(19);
		bean2.setName("tom2");
		
		list.add(bean1);
		list.add(bean2);
		
		byte[] bean1Bytes = otc.serialize(list);
		
		ArrayList<Bean1> list_1 = otc.deserialize(bean1Bytes);
		
		for (Bean1 item : list_1) {
			logger.debug("before serialize bean:"+item+"\n" +
					 "after serialize bean:"+item+" age="+item.getAge()+" name="+item.getName());
			
		}
	}	
}
