package TestRegRex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class TestChina extends TestCase {
	String s = "a2a3a.5";
	
	/**
	 * 汉字判断，
	 * 不包括字符
	 */
	public void test1Chinese(){
	     int count = 0;      
	     // \\u4e00-\\u9fa5汉字                                
	      String regEx = "[\u4e00-\u9fa5]";   
	      
	      // 匹配双字节字符(包括汉字在内) \[^\x00-\xff\] 
	      String regEx2 = "[^\\x00-\\xff]";
	    
	     //System.out.println(regEx);      
	      String str = "中文13s★";
	      Pattern p = Pattern.compile(regEx2);
	      Matcher m = p.matcher(str);      
	     while (m.find()) {      
	         for (int i = 0; i <= m.groupCount(); i++) {      
	              count = count + 1;      
	          }      
	      }      
	      System.out.println("共有 " + count + "个 ");      
	  }     
	
	public void test2(){
		String regEx = "a";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		String s2 = m.group();
		System.out.println(s2);
	}
	
	  public  void test3(){  
	      //create a Pattern  
	       Pattern p = Pattern.compile("B(ond)");  
	   
	      //create a Matcher and use the Matcher.group(int) method  
	      String candidateString = "My name is Bond. James Bond.";  
	      //create a helpful index for the sake of output  
	      Matcher matcher = p.matcher(candidateString);  
	      //Find group number 0 of the first find  
	       matcher.find();  
	       String group_0 = matcher.group(0);  
	       String group_1 = matcher.group(1);  
	       System.out.println("Group 0 " + group_0);  
	       System.out.println("Group 1 " + group_1);  
	       System.out.println(candidateString);  
	   
	      //Find group number 1 of the second find  
	       matcher.find();  
	       group_0 = matcher.group(0);  
	       group_1 = matcher.group(1);  
	       System.out.println("Group 0 " + group_0);  
	       System.out.println("Group 1 " + group_1);  
	       System.out.println(candidateString);  
	   }  
	  /**
	   * test match.group 
	   */
	  public void test4(){
		  String testString = "hello() tom ,hello two jom";
		  String reg = "\\w+";
		  Pattern p = Pattern.compile(reg);
		  Matcher m = p.matcher(testString);
		  System.out.println(m.groupCount());
		  while(m.find()){
			  StringBuffer sb = new StringBuffer();
			  
			  int startIndex = m.start();
			  int endIndex = m.end();
			  int groupCount = m.groupCount();
			  
			  sb.append("start:"+startIndex);
			  sb.append(" end:"+endIndex);
			  // String group = m.group(); // the default is group(0)
			  for (int i = 0;i<=groupCount;i++) {
				sb.append(" group("+i+"):"+m.group(i));
			  }
			  System.out.println(sb.toString());
		  }
		  
	  }
	  /**
	   * byte长度
	   */
	  public void test5ByteLeng(){
		  String s = "a我,。";
		  String s1 = "a";
		  String s2 = "我";
		  String s3 = ",";
		  String s4 = "。";
		  infoByte(s1);
		  infoByte(s2);
		  infoByte(s3);
		  infoByte(s4);
		  
	  }
	  
	  public void infoByte(String s){
		  StringBuffer sb = new StringBuffer();
		  for(int i=0; i<s.getBytes().length;i++){
			  sb.append("byte["+i+"]:"+s.getBytes()[i]+"|");
		  }
		  sb.append("---s1.getBytes().length:"+s.getBytes().length);
		  System.out.println(sb.toString());
	  }
}
