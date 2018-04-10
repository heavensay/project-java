package testhttp.commonhttp;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
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
public class HttpCollect {
//
//	/**
//	 * 基础测试，获取baidu网页信息
//	 * @throws HttpException
//	 * @throws IOException
//	 */
//	@Test
//	public void test1CollectBaidu() throws HttpException, IOException {
//		HttpClient httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器
//		GetMethod getMethod = new GetMethod("http://www.baidu.com");// 创建一个get方法，类似在浏览器地址栏中输入一个地址
//		int statusCode = httpclient.executeMethod(getMethod);// 回车——出拳！
//		System.out.println("response=" + getMethod.getResponseBodyAsString());// 察看拳头命中情况，可以获得的东西还有很多，比如head,
//		getMethod.releaseConnection();// 释放，记得收拳哦
//	}
//
//	@Test
//	public void test2() throws HttpException, IOException {
//		HttpClient httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器
//		GetMethod getMethod = new GetMethod("http://115.29.166.220:8081/investws/user/login?mobilephone=15906644630&mobileAuthcode=111111");
//		int statusCode = httpclient.executeMethod(getMethod);
//		System.out.println("response=" + getMethod.getResponseBodyAsString());
//		getMethod.releaseConnection();
//	}
//
//	@Test
//	public void test3https() throws HttpException, IOException {
//
//		trustAllHosts();
//		HttpClient httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器
//		GetMethod getMethod = new GetMethod("https://127.0.0.1:8443/webfront/data/queryResourceTree");
//		int statusCode = httpclient.executeMethod(getMethod);
//		System.out.println("response=" + getMethod.getResponseBodyAsString());
//		getMethod.releaseConnection();
//	}
//
//
//	/**
//	 * Trust every server - dont check for any certificate
//	 */
//	private static void trustAllHosts() {
//		// Create a trust manager that does not validate certificate chains
//		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//
//			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//
//			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			}
//
//			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			}
//		} };
//
//
//		//这个好像是HOST验证
//		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
//			public boolean verify(String arg0, SSLSession arg1) {
//				return true;
//			}
//			public void verify(String arg0, SSLSocket arg1) throws IOException {}
//			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
//			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
//		};
//
//		// Install the all-trusting trust manager
//		try {
//			SSLContext sc = SSLContext.getInstance("TLS");
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
