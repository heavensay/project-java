package testtaobao.openapi;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TimeGetRequest;
import com.taobao.api.response.TimeGetResponse;

public class TestTBOpenAPI {
	
	/**
	 * 使用讨论sdk jar包
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23524974";
		String secret = "a08de22b3886a273ba9ed11fe3a693fa";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		TimeGetRequest req = new TimeGetRequest();
		TimeGetResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}
	
	@Test
	public void test2() throws Exception{
//		String url = "http://gw.api.tbsandbox.com/router/rest";
		String aliOpenurl = "http://gw.api.taobao.com/router/rest?";
		String appkey = "23524974";
		String secret = "a08de22b3886a273ba9ed11fe3a693fa";
		String format = "json";
		String sign_method = "md5";
		String timestamp = "2016-11-08 21:22:00";
		String v = "2.0";
		String sign = "";
		String method = "taobao.time.get";
		
		Map map = new HashMap();
		map.put("method", method);
		map.put("app_key", appkey);
		map.put("sign_method", sign_method);
		map.put("format", format);
		map.put("timestamp", timestamp);
		map.put("v", v);
		
		sign = signTopRequest(map,secret,sign_method);
		
		StringBuffer urlParam = new StringBuffer();
		urlParam.append("method=").append(method)
		.append("&app_key=").append(appkey)
		.append("&format=").append(format)
		.append("&sign_method=").append(sign_method)
		.append("&timestamp=").append(timestamp)
		.append("&v=").append(v)
		.append("&sign=").append(sign);
		
		String ss = URLEncoder.encode(urlParam.toString(), "UTF-8");
		//http://gw.api.taobao.com/router/rest?method=taobao.time.get&app_key=23524974&format=json&sign_method=md5&timestamp=2016-11-08%2021:22:00&v=2.0&sign=9C2E2C81FC46CA7B07DD18FD82F70D22
		CloseableHttpClient httpclient = HttpClients.createDefault();// 创建一个客户端，类似打开一个浏览器
		
		URL url = new URL(aliOpenurl+urlParam.toString());
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		HttpGet getReq = new HttpGet(uri);
		
		HttpResponse response = httpclient.execute( getReq);
        HttpEntity entity = response.getEntity();
        
        // 获取响应实体    
        System.out.println("--------------------------------------");  
        // 打印响应状态    
        System.out.println(response.getStatusLine());  
        if (entity != null) {  
            // 打印响应内容长度    
            System.out.println("Response content length: " + entity.getContentLength());  
            // 打印响应内容    
            System.out.println("Response content: " + EntityUtils.toString(entity,"UTF-8"));  
        }  
        System.out.println("------------------------------------");
        httpclient.close();
	}
	
	
	public static String signTopRequest(Map<String, String> params, String secret, String signMethod) throws IOException, NoSuchAlgorithmException {
	    // 第一步：检查参数是否已经排序
	    String[] keys = params.keySet().toArray(new String[0]);
	    Arrays.sort(keys);
	 
	    // 第二步：把所有参数名和参数值串在一起
	    StringBuilder query = new StringBuilder();
	    if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
	        query.append(secret);
	    }
	    for (String key : keys) {
	        String value = params.get(key);
	        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
	            query.append(key).append(value);
	        }
	    }
	 
	    // 第三步：使用MD5/HMAC加密
	    byte[] bytes;
	    if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
	        bytes = encryptHMAC(query.toString(), secret);
	    } else {
	        query.append(secret);
	        System.out.println("========="+query.toString());
	        bytes = encryptMD5(query.toString());
	    }
	 
	    // 第四步：把二进制转化为大写的十六进制
	    return byte2hex(bytes);
	}
	
	 
	public static byte[] encryptHMAC(String data, String secret) throws IOException {
	    byte[] bytes = null;
	    try {
	        SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
	        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
	        mac.init(secretKey);
	        bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
	    } catch (GeneralSecurityException gse) {
	        throw new IOException(gse.toString());
	    }
	    return bytes;
	}
	 
	public static byte[] encryptMD5(String data) throws IOException, NoSuchAlgorithmException {
	   MessageDigest md = MessageDigest.getInstance("MD5");
	   byte[] array = md.digest(data.getBytes(Constants.CHARSET_UTF8));
	    return array;
	}
	 
	public static String byte2hex(byte[] bytes) {
	    StringBuilder sign = new StringBuilder();
	    for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(bytes[i] & 0xFF);
	        if (hex.length() == 1) {
	            sign.append("0");
	        }
	        sign.append(hex.toUpperCase());
	    }
	    return sign.toString();
	}
	
	static class Constants{
		public static String CHARSET_UTF8 = "UTF-8";
		public static String SIGN_METHOD_MD5 = "md5";
		public static String SIGN_METHOD_HMAC = "hmac";
	}
}


