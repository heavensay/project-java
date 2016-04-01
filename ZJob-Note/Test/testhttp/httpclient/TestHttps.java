package testhttp.httpclient;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
public class TestHttps {
	
	@Test
	public void test0xnjks() throws Exception {
		InputStream ins = new FileInputStream("E:/store/all/xn.cer");
		// 读取证书
		CertificateFactory cerFactory = CertificateFactory.getInstance("X.509"); // 问1
		Certificate cer = cerFactory.generateCertificate(ins);
		// 创建一个证书库，并将证书导入证书库
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 问2
		keyStore.load(null, null);
		keyStore.setCertificateEntry("trust", cer);
		
		
        SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);  
        //不校验域名  
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
        Scheme sch = new Scheme("https", socketFactory, 443);  

        
        HttpClient httpclient = new DefaultHttpClient();// 创建一个客户端，类似打开一个浏览器
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		
		HttpGet getReq = new HttpGet("https://127.0.0.1:8443/webfront/data/queryResourceTree");
		HttpResponse response = httpclient.execute( getReq);
		
        HttpEntity entity = response.getEntity();
        
        System.out.println(response);
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine()+","+entity.getContent());
        System.out.println(EntityUtils.toString(entity));
        
        System.out.println(entity.toString());
		
        httpclient.getConnectionManager().shutdown();
	}
	@Test
	public void test0xnbks() throws Exception {
		InputStream ins = new FileInputStream("E:/store/all/xn.cer");
		// 读取证书
		CertificateFactory cerFactory = CertificateFactory.getInstance("X.509"); // 问1
		Certificate cer = cerFactory.generateCertificate(ins);
		// 创建一个证书库，并将证书导入证书库
		KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC"); // 问2
		keyStore.load(null, null);
		keyStore.setCertificateEntry("trust", cer);
		
		
        SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);  
        //不校验域名  
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
        Scheme sch = new Scheme("https", socketFactory, 443);  

        
        HttpClient httpclient = new DefaultHttpClient();// 创建一个客户端，类似打开一个浏览器
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		
		HttpGet getReq = new HttpGet("https://127.0.0.1:8443/webfront/data/queryResourceTree");
		HttpResponse response = httpclient.execute( getReq);
		
        HttpEntity entity = response.getEntity();
        
        System.out.println(response);
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine()+","+entity.getContent());
        System.out.println(EntityUtils.toString(entity));
        
        System.out.println(entity.toString());
		
        httpclient.getConnectionManager().shutdown();
	}
	
	@Test
	public void test1httpsdx() throws Exception {
		InputStream ins = new FileInputStream("D:/tomcat_ca.cer");
		// 读取证书
		CertificateFactory cerFactory = CertificateFactory.getInstance("X.509"); // 问1
		Certificate cer = cerFactory.generateCertificate(ins);
		// 创建一个证书库，并将证书导入证书库
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 问2
		keyStore.load(null, null);
		keyStore.setCertificateEntry("trust", cer);
		
		
        SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);  
        //不校验域名  
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
        Scheme sch = new Scheme("https", socketFactory, 443);  

        
        HttpClient httpclient = new DefaultHttpClient();// 创建一个客户端，类似打开一个浏览器
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		
		HttpGet getReq = new HttpGet("https://127.0.0.1:8443/webfront/data/queryResourceTree");
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
	 * 不验证证书，即信任所有服务器下发的证书
	 * @throws Exception
	 */
	@Test
	public void test3httpsNoAuthCer() throws Exception {

		// 初始化https连接环境
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };
		
		HttpClient httpclient = new DefaultHttpClient();
		// Secure Protocol implementation.
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, trustAllCerts, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		//信任所有主机
		//没有这句，会报错。javax.net.ssl.SSLException: hostname in certificate didn't match:
		ssf.setHostnameVerifier(new AllowAllHostnameVerifier());
		ClientConnectionManager ccm = httpclient.getConnectionManager();
		// register https protocol in httpclient's scheme registry
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));

		HttpGet getReq = new HttpGet(
				"https://127.0.0.1:8443/webfront/data/queryResourceTree");
		HttpResponse response = httpclient.execute(getReq);

		HttpEntity entity = response.getEntity();

		System.out.println(response);
		System.out.println("----------------------------------------");
		System.out
				.println(response.getStatusLine() + "," + entity.getContent());

		httpclient.getConnectionManager().shutdown();
	}
	
}
