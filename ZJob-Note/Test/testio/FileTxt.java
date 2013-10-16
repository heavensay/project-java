package testio;

import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

public class FileTxt extends TestCase{
	public void test1() throws Exception{
		File file = new File("DOC/5.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		os.write("abc".getBytes());
		os.close();
	}

	public void test2() throws Exception{
		File file = new File("C:\\Documents and Settings\\Administrator\\桌面\\impdata.txt");
		FileInputStream in  = new FileInputStream(file);
		int count = in.read();
		while(count!=-1){
			System.out.println((char)count);
			count = in.read();
		}
	}
}
