package test.common;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;


public class TestDate {
	@Test
	public void test1() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd HHmmss");
		Date date = sdf.parse("20110101 102030");
		Timestamp t = new Timestamp(date.getTime());
		System.out.println("123444555666");
		System.out.println(date.equals(t));
		System.out.println(t.equals(date));
        System.out.println(3);
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

	/**
	 * java的TimeZone对象给我们的是原始的偏移量，也就是与GMT相差的微秒数，
	 * Java的Date对象里面存储着当前时刻到1970年1月1日0:00所经过的毫秒数，它与时区和地域没有关系(其实可以认为是GMT时间)。
	 * 计算机内部记录的时间(Date date = new Date())，为格林威治标准时(GMT)。
	 * 即java.util.Date代表一个时间点，其值为距公元1970年1月1日 00:00:00的毫秒数，所以它可以认为是没有时区和Locale概念的。
	 *
	 *
	 * DateFormat：日期格式化类DateFormat， 对于不同地区的配置一般有两个点, 一个是Locale , 一个是TimeZone
	 * locale： Locale使DateFormat按所配置的地区特性来输出文字(例如中国,美国,法国不同地区对日期的表示格式不一样,中国可能是2001年10月5日)
	 * TimeZone：让DateFormat知道怎么去转换，去调整时间偏移度，从而得到符合配置的时区的时间。
	 */
	@Test
	public void testTimezone(){
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		System.out.println(format.format(now));

		TimeZone GMT = TimeZone.getTimeZone("GMT+:08:00");
		format.setTimeZone(GMT);
		System.out.println("GMT:英国伦敦本初子午线："+format.format(now));

		TimeZone UTC = TimeZone.getTimeZone("UTC");
		format.setTimeZone(UTC);
		System.out.println("UTC世界协调时间,UTC是0时区的时间:"+format.format(now));

		TimeZone CST = TimeZone.getTimeZone("CST");
		format.setTimeZone(CST);
		System.out.println("CST 中国标准时间(北京时间)，在东八区："+format.format(now));
	}
}
