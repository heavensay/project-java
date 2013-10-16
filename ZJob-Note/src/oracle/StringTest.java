package oracle;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


public class StringTest {
	
	@Test
	public void test1() throws Exception{
		String str = "16(552232,9231239,6123126) ,17(44,55,32)";
		String code = "sz99,sz13,ads,sh16,sz28" ;
		String reg = "(\\w+)[\\(]([^\\)]+)[\\)]";
//		String reg = "(16|32)[\\(]([^\\)]+)[\\)]";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		
		String[] codes = code.split(",");
		
		//String[] sh = new String[]{"16","17","18"};
		
		String[] shs =null;
		String[] szs = null;
		
			StringBuffer sbuffer = new StringBuffer();
			
			//这2个标记表示t_user_stock 的数据是否已经有这2个模块了,如果没有，则创建
			boolean sh = false;
			boolean sz = false;
			
			while (m.find()) {
				String sign = null;
				
				skip:
				for (int i = 0; i <= m.groupCount(); i++) {
					
					switch (i) {
					case 0:
						break;
					case 1:
 						if("16".equals(m.group(i))){
 							sbuffer.append("16");
							sign= "sh";
							sh = true;
						}else if("32".equals(m.group(i))){
							sbuffer.append("32");
							sign = "sz";
							sz = true;
						}else{
							//如果不是sh、sz模块，直接加到字符串里面
							sbuffer.append(m.group()+";");
							break skip;
						}
						break;
					case 2:
						shs = m.group(i).split(",");
						List<String> list = new ArrayList<String>(Arrays.asList(shs));
						for (String c0 : codes) {
							if(c0.startsWith(sign)){
								if(!list.contains(c0))
									list.add(c0);
							}
						}
						String s = convertArraytoString(list,",");
						sbuffer.append(s==null?"":("("+s+");"));	
						
						break;
					default:
						throw new Exception("格式错误");
					}

				}
			}
			
			
			for (String c : codes) {
				if(!sz){
					int count=0;
					List list = new ArrayList();
					for (String c0 : codes) {
						if(c0.startsWith("sz")){
							if(count==0){
								sbuffer.append("32");
								sz = true;
								count++;
							}
							if(!list.contains(c0)){
								list.add(c0);
							}
						}
					}
					if(count!=0){
						String s = convertArraytoString(list,",");
						sbuffer.append(s==null?"":("("+s+");"));	
					}
				}
			}
			System.out.println(sbuffer.substring(0, sbuffer.length()-1).toString());
	}
	
	private String convertArraytoString(List list,String separator){
		if (list.size() == 0)
			return null;
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i));
			sb.append(separator);
		}
		String s = sb.substring(0, sb.length()-1);
		return s;
	}
	
	
	@Test
	public void test2(){
		 Pattern p = Pattern.compile("c(at)");
		 Matcher m = p.matcher("one cat two cats in the yard");
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
			 for(int i=0;i<m.groupCount();i++){
				 
			 }
		     m.appendReplacement(sb, "dog");
		    // sb.delete(3,6);
		 }
		 m.appendTail(sb);
		 System.out.println(sb.toString());
	}
	
	@Test
	public void test3(){
		 Pattern p = Pattern.compile("c(a)(t)");
		 Matcher m = p.matcher("dcatdd catp");
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
			 for(int i=0;i<=m.groupCount();i++){
				 System.out.println( String.format("group : {%d},m.start:%d ,m.end:%d  output:%s"  ,i,m.start(i),m.end(i),m.group(i)));
			 }
		    // sb.delete(3,6);
		 }
		// m.appendTail(sb);
		 //System.out.println(sb.toString());
	}
	
	@Test
	public void test4(){
		String[] s = new String[]{"aa","bb"};
		List list = Arrays.asList(s);
		System.out.println(list.size());
	}
	
	@Test
	public void test5(){
		
		for(int i = 0;i<10;i++){
			d:	
			for(int j=0;j<20;j++){
				switch (j){
				case 0:
					System.out.println("i:"+i+"   j:"+j);
					break;
				case 5:
					if(j==3){
						break d;
					}
					break;
				case 6:
					System.out.println("i:"+i+"   j:"+j);
					break;
				}
			}
		System.out.println("out of the firt loop, in of the second loop");
		}
	}
	
	@Test
	public void test6() throws Exception{
		String str = "16(552232,9231239,6123126) ,17(44,55,32),32(11,33,3335,)";
//		String reg = "(\\w+)[\\(]([^\\)]+)[\\)]";
		String reg = "(16|32)[\\(]([^\\)]+)[\\)]";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		
			StringBuffer sbuffer = new StringBuffer();
			
			while (m.find()) {
				String sign = null;
				
				System.out.println(m.groupCount());
				
				skip:
				for (int i = 0; i <= m.groupCount(); i++) {
					System.out.println(String.format("group({%d})=%s", i,m.group(i)));
				}
			}
	}
	@Test
	public void test7(){
		String s = "abc";
		Map map = new HashMap();
		map.put(1, s);
		s = "dddd";
		System.out.println(map.get(1));
	}
	
	@Test
	public void test8(){
		System.out.println(String.format("a%sa", "bbbb"));
	}
	
	@Test
	public void test9() throws Exception{
		int i = 17 ;
		int a = 0x20;
		int i2 = 35 & 32;
		System.out.println(i2);
	}
	@Test
	public void test91() throws Exception{
		String s = "国信证券";
		System.out.println(URLEncoder.encode(s, "UTF-8"));
	}
}
