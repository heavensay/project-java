package common;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class CommonUtil {
	
	public static void println(Object any){
		System.out.println(any);
	}
	
	@Test
	public void test() throws IOException{
		File file = new File("E:/1.txt");
		file.createNewFile();
		
	}
	
}
