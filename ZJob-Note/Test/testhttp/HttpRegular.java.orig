package testhttp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class HttpRegular {
	
	/**
	 * 正则替换图片地址
	 */
	@Test
	public void test1ImageReplace(){
        String   regEx=" src=\"?(.*?)(\"|>|\\s+)"    ;
        String   str= " <img   alt=\"tyui\"   border=\"0\" src=\"http://www.sina.com.cn\">"; 
        Pattern   p=Pattern.compile(regEx); 
        Matcher   m=p.matcher(str); 
        String   s=m.replaceAll( "str=\"aaa\" ");   //   ( " ")   删除 
        System.out.println(s); 
	}
}
