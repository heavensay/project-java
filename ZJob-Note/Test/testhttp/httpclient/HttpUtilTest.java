package testhttp.httpclient;

import org.junit.Test;

public class HttpUtilTest {

	@Test
	public void httpsWithNoAuthTest(){
		String httpUrl = "https://www.baidu.com";
		String result = HttpUtil.get(httpUrl);
		System.out.println(result);
	}
	
	@Test
	public void httpsOtherPortTest(){
		String httpUrl = "https://172.20.200.8:8002/hexin-crm/rs/rs.do?method=staffHasTask&jobnum=T123";
		String result = HttpUtil.get(httpUrl);
		System.out.println(result);
	}
}
