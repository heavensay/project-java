package testhttp.httpclient;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 使用 HttpClient 需要以下 6 个步骤：
	1. 创建 HttpClient 的实例
	2. 创建某种连接方法的实例，在这里是 GetMethod。在 GetMethod 的构造函数中传入待连接的地址
	3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
	4. 读 response
	5. 释放连接。无论执行方法是否成功，都必须释放连接
	6. 对得到后的内容进行处理
 * @author banana
 *
 */
public class TestHttp {
	
	/**
	 * 构造post请求
	 * @throws Exception
	 */
	@Test
	public void test1Get() throws Exception {
        
        HttpClient httpclient = new DefaultHttpClient();// 创建一个客户端，类似打开一个浏览器
		HttpGet getReq = new HttpGet("http://192.168.51.95:8080/hexin-crm/rs/rs.do?method=certificate&email=abc&token=ggg");
		
		HttpResponse response = httpclient.execute( getReq);
		
        HttpEntity entity = response.getEntity();
        
        System.out.println(response);
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine()+","+entity.getContent());
        System.out.println(EntityUtils.toString(entity));
        System.out.println(entity.toString());
        httpclient.getConnectionManager().shutdown();
	}
	
	/**
	 * 构造post请求
	 * @throws Exception
	 */
	@Test
	public void test2Post() throws Exception {
        
        HttpClient httpclient = new DefaultHttpClient();// 创建一个客户端，类似打开一个浏览器
		HttpPost postReq = new HttpPost("http://192.168.51.95:8080/hexin-crm/rs/rs.do?method=certificate");
		
		HttpResponse response = httpclient.execute( postReq);
		
        HttpEntity entity = response.getEntity();
        
        System.out.println(response);
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine()+","+entity.getContent());
        System.out.println(EntityUtils.toString(entity));
        System.out.println(entity.toString());
        httpclient.getConnectionManager().shutdown();
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3URIBuilder() throws Exception {
//        String url = "http://www.baidu.com";
        String url = "http://www.baidu.com/book?method=query";
        String rightUrl = "http://www.baidu.com/book?method=query&name=jack&id=1";
        
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		BasicNameValuePair pair1 = new BasicNameValuePair("name","jack");
		BasicNameValuePair pair2 = new BasicNameValuePair("id","1");
		List list = new ArrayList();
		list.add(pair1);
		list.add(pair2);
		ub.setParameters(list);
		
		URI u1 = URI.create(url);
		URI u2 = ub.build();
		URI u3 = new URIBuilder(u1).addParameters(list).build();//right，推荐这种拼接法
		URI u4 = new URIBuilder(u1).addParameters(list).setParameters(list).build();//method丢失
		
		URI u5 = new URIBuilder("http://www.baidu.com").addParameters(list).build();//
		URI u6 = new URIBuilder("http://www.baidu.com/").addParameters(list).build();
		URI u7 = new URIBuilder("http://www.baidu.com?").addParameters(list).addParameters(list).build();
		
		System.out.println(u1.toString());
		System.out.println(u2.toString());
		System.out.println(u3.toString());
		System.out.println(u4.toString());
		System.out.println(u5.toString());
		System.out.println(u6.toString());
		System.out.println(u7.toString());
		Assert.assertEquals("url拼接错误", rightUrl,u3.toString());
	}
	
	
}
