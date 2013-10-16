import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


public class TestDate {
	@Test
	public void test1() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd HHmmss");
		Date date = sdf.parse("20110101 102030");
		Timestamp t = new Timestamp(date.getTime());
		System.out.println(date.equals(t));
		System.out.println(t.equals(date));
	}
	@Test
	public void test2() throws Exception{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMDD HHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd HHmmss");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyymmdd HHmmss");
		Timestamp t = new Timestamp(1315298256000l);
		//1294303056000l
		Date date = new Date(1315298256000l);
		System.out.println(t);
		System.out.println(date + " yyyyMMDD HHmmss "+sdf1.format(date));
		//output:Tue Sep 06 16:37:36 CST 2011 yyyyMMDD HHmmss 201109249 163736
		System.out.println(date + " yyyyMMdd HHmmss "+sdf2.format(date));
		//output:Tue Sep 06 16:37:36 CST 2011 yyyyMMdd HHmmss 20110906 163736
		System.out.println(date + " yyyymmdd HHmmss "+sdf3.format(date));
		//output:Tue Sep 06 16:37:36 CST 2011 yyyymmdd HHmmss 20113706 163736
	}
	@Test
	public void test3() throws Exception{
		System.out.println(new Date(2012,6,11).getTime());
		java.sql.Date sqlDate=java.sql.Date.valueOf("2012-06-11");
		
		System.out.println(sqlDate.getTime());
		System.out.println(new Date(1339344000000L));
	}
	
	/**
	 * 2个日期之间想减 ，得到天数，并且测试 相处 余数的边界。
	 * @throws Exception
	 */
	@Test
	public void test4twoDaysOperation() throws Exception{
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date date2 = sdf.parse("20120708 010203");

		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1
				.getTime() - date2.getTime())
				/ (24 * 60 * 60 * 1000)
				: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
		System.out.println(-2 / 3 + "---" + 6 / 5);
		System.out.println(-8/3);
		
		System.out.println(day);
	}
	
	@Test
	public void test5() throws Exception{
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		Timestamp t1 = new Timestamp(System.currentTimeMillis()-10000000L);
		Timestamp t2 = new Timestamp(System.currentTimeMillis()+100000000L);
		
		String s1 = sdf.format(t1);
		String s2 = sdf.format(t2);
		
		long radix = 1000*60*60*24 ; //一天
		
		long  a = System.currentTimeMillis();
		long  b = System.currentTimeMillis()+radix*2-1;
		
		System.out.println((b-a)/ (1000 * 60 * 60 * 24));
		
	}
	/**
	 * test add beween two date 
	 */
	@Test
	public void test6(){
		Calendar c = Calendar.getInstance();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		c.setTime(time);
		
		c.add(Calendar.DAY_OF_WEEK, 8);
		Date d = c.getTime();
		System.out.println(d);
		
	}
}
