package testhttp.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
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
	
	/**
	 * 加载证书到证书库中
	 * @throws Exception
	 */
	@Test
	public void httpsLoadCerTest() throws Exception {
		InputStream ins = new FileInputStream("E:/store/crm/crm8002.cer");
		// 读取证书
		CertificateFactory cerFactory = CertificateFactory.getInstance("X.509"); // 问1
		Certificate cer = cerFactory.generateCertificate(ins);
		// 创建一个证书库，并将证书导入证书库
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 问2
		keyStore.load(null, null);
		keyStore.setCertificateEntry("trust-test", cer);
		
        SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);  
        //不校验域名  
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
        Scheme sch = new Scheme("https", socketFactory, 443);  

        HttpClient httpClient = new DefaultHttpClient();// 创建一个客户端，类似打开一个浏览器
        httpClient.getConnectionManager().getSchemeRegistry().register(sch);
		
		String httpsAauthorityCerUrl = "https://www.baidu.com/";//权威认证证书
		String httpsCustomCerUrl = "https://172.20.200.8:8002/hexin-crm/rs/rs.do?method=staffHasTask&jobnum=T123";//服务器使用keytool工具生产的自制证书
		String[] urls = new String[]{httpsAauthorityCerUrl,httpsCustomCerUrl};
		
		for (String url : urls) {
			String result = null;
			try{
				HttpGet getReq = new HttpGet(url);
				HttpResponse response = httpClient.execute(getReq);
				result = EntityUtils.toString(response.getEntity(),"UTF-8");
				System.out.println("url:"+url+"访问成功 result:"+result);
			}catch(Exception e){
				System.out.println("url:"+url+"访问失败:"+e);
				e.printStackTrace(System.out);
			}
		}
	}
	
	/**
	 * pkcs12库测试
	 * @throws Exception
	 */
	@Test
	public void pkcsTest() throws Exception {
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
	
	/**
	 * 信任所有服务器下发的证书，即不验证证书，安全性降低(慎用)
	 * @throws Exception
	 */
	@Test
	public void httpsTrustAllCerTest() throws Exception {

		// 初始化https连接环境
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {}
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {}
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

		String httpsUrl = "https://www.baidu.com/";
		HttpGet getReq = new HttpGet(httpsUrl);
		HttpResponse response = httpclient.execute(getReq);

		HttpEntity entity = response.getEntity();

		System.out.println(response);
		System.out.println("----------------------------------------");
		System.out
				.println(response.getStatusLine() + "," + entity.getContent());

		httpclient.getConnectionManager().shutdown();
	}
	
	/**
	 * 访问自定义证书网站，客户端加载的证书库信任自定义证书
	 * 
	 * myhexin.keystore已经加载了https://172.20.200.8:8002网站的证书，不包括其他证书
	 * 访问https://www.baidu.com/报错:javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
	 * 访问https://172.20.200.8:8002，没问题
	 * 
	 * @throws Exception
	 */
	@Test
	public void httpsLoadCustomKeyStoreTest() throws Exception {
		File keyStoreFile = new File("E:/store/crm/myhexin.keystore");
        SSLContext sc = SSLContexts.custom().loadTrustMaterial(keyStoreFile, "123456".toCharArray()).build();
        //NoopHostnameVerifier  https//不校验域名
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(  
                sc,NoopHostnameVerifier.INSTANCE);
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
				.build();
		String httpsAauthorityCerUrl = "https://www.baidu.com/";//权威认证证书
		String httpsCustomCerUrl = "https://172.20.200.8:8002/hexin-crm/rs/rs.do?method=staffHasTask&jobnum=T123";//服务器使用keytool工具生产的自制证书
		String[] urls = new String[]{httpsAauthorityCerUrl,httpsCustomCerUrl};
		
		for (String url : urls) {
			String result = null;
			try{
				HttpGet getReq = new HttpGet(url);
				HttpResponse response = httpClient.execute(getReq);
				result = EntityUtils.toString(response.getEntity(),"UTF-8");
				System.out.println("url:"+url+"访问成功 result:"+result);
			}catch(Exception e){
				System.out.println("url:"+url+"访问失败:"+e);
				e.printStackTrace(System.out);
			}
		}
	}
	
	/**
	 * 访问自定义证书网站，客户端加载的证书库信任自定义证书
	 * 测试结果来看实际使用的是第一次调用loadTrustMaterial中的keystore
	 * @throws Exception
	 */
	@Test
	public void httpsLoadMulKeyStoreTest() throws Exception {
		File keyStoreFile = new File("E:/store/crm/myhexin.keystore");
		File jvmDefaultStoreFile = new File("E:/banana/software/jdk1.7.0_07/jre/lib/security/cacerts");

		//测试结果来看实际使用的是第一次调用loadTrustMaterial中的keystore
        SSLContext sc = SSLContexts.custom()
        				.loadTrustMaterial(jvmDefaultStoreFile, "changeit".toCharArray())
        				.loadTrustMaterial(keyStoreFile, "123456".toCharArray())
        				.build();
        
        //NoopHostnameVerifier  https//不校验域名
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(  
                sc,NoopHostnameVerifier.INSTANCE);
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
				.build();
		String httpsAauthorityCerUrl = "https://www.baidu.com/";//权威认证证书
		String httpsCustomCerUrl = "https://172.20.200.8:8002/hexin-crm/rs/rs.do?method=staffHasTask&jobnum=T123";//服务器使用keytool工具生产的自制证书
		String[] urls = new String[]{httpsAauthorityCerUrl,httpsCustomCerUrl};
		
		for (String url : urls) {
			String result = null;
			try{
				HttpGet getReq = new HttpGet(url);
				HttpResponse response = httpClient.execute(getReq);
				result = EntityUtils.toString(response.getEntity(),"UTF-8");
				System.out.println("url:"+url+"访问成功 result:"+result);
			}catch(Exception e){
				System.out.println("url:"+url+"访问失败:"+e);
				e.printStackTrace(System.out);
			}
		}
	}

}
