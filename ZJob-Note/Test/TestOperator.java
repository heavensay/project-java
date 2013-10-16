import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TestOperator {
	@Test
	public void test1(){
		int value10 = Integer.valueOf("78640", 16);
		System.out.println(value10);
	}

	/**
	 * 按位操作 
	 * e.g 1=1,2=2,3=1+2,4=4,5=1+4,6=2+4,7=1+2+4
	 *  7  111
	 * =
	 *  1  001
	 * +2  010
	 * +4  100
	 */
	@Test
	public void test3(){
		String s = "6";
		int orig = Integer.parseInt(s);
		String binary = Integer.toBinaryString(Integer.parseInt(s));
		int length = binary.length();
		int count = length;
		
		char[] templet = new char[length];
			for(int i=0;i<count;i++){
				templet[i]='0';
			}
		
		count = length;
		while(count>0){
			templet[count-1] ='1'; 
			int s1 = orig & Integer.valueOf(String.valueOf(templet),2);
			templet[count-1] ='0'; 
			count--;
			System.out.println(s1);
		}
	}
		
}
