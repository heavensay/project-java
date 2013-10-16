import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import junit.framework.TestCase;


public class RandomTest extends TestCase {
	public void void1random(){
		Random rd  = new Random();
		for(int i = 0;i<100;i++)
		System.out.println(rd.nextInt(10));
	}
	
	public void test2F(){
		Integer a = new Integer(5);
		Integer b = new Integer(6);
		Integer c = new Integer(7);
		Integer d = new Integer(5);
		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(a));
		System.out.println(a.compareTo(d));
	}
	
	public void test3bytes() throws Exception{
		File file = new File("C:\\Documents and Settings\\Administrator\\桌面\\3123.txt");
		InputStream is = new FileInputStream(file);
		byte[] b = new byte[1024];
		StringBuffer sb = new StringBuffer(1024*10);
		while(is.read(b) != -1){
		//	System.arraycopy(b, 0, b, destPos, length)
			sb.append(new String(b));
		}
		System.out.println(sb.toString());
	}
	
	public void test4(){
		System.out.println( Charset.defaultCharset().name());
		System.out.println(Integer.toString(-17, 2));
		System.out.println(Integer.parseInt("BB", 16));
		System.out.println(Integer.toBinaryString(-17));
		System.out.println(Integer.toBinaryString(-69));
		System.out.println(Integer.toBinaryString(239));
	}
	public void testuuid(){
		System.out.println(UUID.randomUUID().toString());
	}
	public void test5(){
		for(int i=0;i<5;i++){
			try{
				if(i==2){
					throw new Exception("asd");
				}
				System.out.println("normal "+i);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				System.out.println("finally" + i);
			}
			System.out.println("finally later");
		}
		System.out.println("after Exception");
	}
	public void test6(){
		String s = "a,b";
		System.out.println(s.indexOf("c"));
		System.out.println(s.substring(0,1));
	}
	/**
	 * ���� catch �� �ܷ� ʵ��return
	 * �Ƿ��ִ�е�finally 
	 * 
	 * the result : ����ִ��return
	 */
	public void test7(){
		String s = f();
		System.out.println(s);
	}
	
	public String f(){
		try{
			throw new Exception("aaa");
		}catch(Exception e){
			System.out.println(" catch ");
			return "s1";
		}finally{
			System.out.println(" finally ");
			return "s2";
		}
		//return "s3"; //finally �� ��return ����ͱ��벻��
	}
	
	public void testMerge(){
		List list = new ArrayList();
		String[] ss = new String[]{"a","",null,"b","e","c","b","a"};
		for(int i=0;i<ss.length;i++){
			if(ss[i]==null)
				continue;
			list.add(ss[i]);
			for(int j=i+1;j<ss.length;j++){
				if(ss[i].equals(ss[j])){
					ss[j]=null;
				}
			}
		}
		System.out.println(list.toArray(new String[list.size()]));
	}
	
	public void testFor(){
		for (int i = 0; i < 10; i++) {
			System.out.println( "i :"+i);
			for (int j = 10; j > 0; j--) {
				if(i==j) break;
				System.out.println( "i :"+i+"  j:"+j );
			}
			break;
			//System.out.println("-----");
		}
		System.out.println("end");
	}
	
}
