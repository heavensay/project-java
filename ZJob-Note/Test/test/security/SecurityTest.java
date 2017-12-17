package test.security;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Test;

public class SecurityTest {
	
	/**
	 * 打印JVM默认使用的keystore中信任的证书
	 * @throws Exception
	 */
	@Test
	public void printSystemDefaultKeyStore() throws Exception{
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream ins = new FileInputStream("E:/banana/software/jdk1.7.0_07/jre/lib/security/cacerts");//%JAVA_HOME%\jre\lib\security\cacerts
		// 加载密钥库
		keyStore.load(ins,"changeit".toCharArray());
		
		Enumeration<String> enums = keyStore.aliases();
		while(enums.hasMoreElements()){
			String s = enums.nextElement();
			System.out.println("alias:"+s);
		}
	}
	
	/**
	 * Get the list of all supported cipher suites.
	 */
	@Test
	public void getSupportedCipher() throws Exception{
		// Get the SSLServerSocket
		SSLServerSocketFactory ssl;
		SSLServerSocket sslServerSocket;
		ssl = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		sslServerSocket = (SSLServerSocket) ssl.createServerSocket();

		// Get the list of all supported cipher suites.
		String[] cipherSuites = sslServerSocket.getSupportedCipherSuites();
		for (String suite : cipherSuites)
		  System.out.println(suite);

		// Get the list of all supported protocols.
		String[] protocols = sslServerSocket.getSupportedProtocols();
		for (String protocol : protocols)
		  System.out.println(protocol);
	}
	
	/**
	 * 使用Keystore中密钥对进行数据加解密
	 * 
	 * @throws Exception
	 */
	@Test
	public void encryAndDescryByKeyStoreTest() throws Exception{
		String keyStorePath = "E:/store/crm/myhexin.keystore";
		String alias = "myhexin";
		String storePass= "123456";
		String keyPass = "123456";
		
		String str = "aabb";
		
		String publicKeyString = KeyStoreCoder.getStrPublicKey(keyStorePath, alias, storePass);
		String privateKeyString = KeyStoreCoder.getStrPrivateKey(keyStorePath, alias, storePass, keyPass);
		System.out.println("公钥 :"+publicKeyString);
		System.out.println("私钥:"+privateKeyString);
		
		
		System.out.println("开始加密 str："+str);
		String encryStr = KeyStoreCoder.encryptByPublicKey(publicKeyString, str);
		System.out.println("公钥加密后值："+encryStr);
		String descryptStr = KeyStoreCoder.descryptByPrivateKey(privateKeyString, encryStr);
		System.out.println("私钥解密后值"+descryptStr);
		
	}
	
	/**
	 * 加入ssl层
	 * 信任所有服务器下发的证书，即不验证证书，安全性降低(慎用)
	 * 
	 * @throws Exception
	 */
	@Test
	public void sslTrustAllCerTest() throws Exception {

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
		
		// Secure Protocol implementation.
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, trustAllCerts, null);
		SSLSocketFactory ssf = ctx.getSocketFactory();
		
//		String httpsUrl = "https://172.20.200.8:8002/hexin-crm/rs/rs.do?method=staffHasTask&jobnum=T123";
		String httpsUrl = "https://www.baidu.com";
		URL realUrl = new URL(httpsUrl);
        // 打开和URL之间的连接
        HttpsURLConnection connection = (HttpsURLConnection)realUrl.openConnection();
        connection.setSSLSocketFactory(ssf);
        connection.setHostnameVerifier(new HostnameVerifier() {
			//证书域名与实际访问域名不检验
			@Override
			public boolean verify(String s, SSLSession sslsession) {
				// TODO Auto-generated method stub
				System.out.println("hostname :"+s);
				return true;
			}
		});
        
		Object content = connection.getContent();
		InputStream in = connection.getInputStream();
		BufferedReader reader =  new BufferedReader(new InputStreamReader(in,"utf-8"));
		
		System.out.println("content encoding:"+connection.getContentEncoding());
		
		String line = null;
		while((line = reader.readLine()) != null ){
			System.out.println(line);
		}
	}
}
