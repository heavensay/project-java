package testhttp.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	protected static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private static PoolingHttpClientConnectionManager cm;
	private static CloseableHttpClient httpClient;
	private static String CHAR_SET = "UTF-8";

    /**
     * 最大连接数400 
     */
    private static int MAX_CONNECTION_NUM = 400;  
  
  
    /** 
     * 单路由最大连接数80 
     */  
    private static int MAX_PER_ROUTE = 80;
  
  
    /** 
     * 超时时间(单位:毫秒) 
     */  
    private static int MAX_TIME_OUT = 15000;  
  
  
    private static Object LOCAL_LOCK = new Object();
    
    
    // 配置请求的超时设置  其他参数可以追加  
    private static RequestConfig REQUEST_CONFIG = RequestConfig.custom()  
            .setConnectionRequestTimeout(MAX_TIME_OUT)// 设置从连接池获取连接实例的超时  
            .setConnectTimeout(MAX_TIME_OUT)// 设置连接超时  
            .setSocketTimeout(MAX_TIME_OUT)// 设置读取超时  
            .build(); 
    
    
    static{
    	init();
    }
    
	private static void init() {
		final String methodName = "init";
        logger.debug(methodName, " initPoolManager");  
        if (null == cm) {
            synchronized (LOCAL_LOCK) {
                if (null == cm) {
                    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();  
                    try {
                        sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                        //NoopHostnameVerifier  https即不验证主机名
                        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(  
                                sslContextBuilder.build(),NoopHostnameVerifier.INSTANCE);  
                        
                        // 创建SSLSocketFactory
                        // 定义socket工厂类    指定协议（Http、Https）
                        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()  
                                .register("https", socketFactory) 
                                .register("http", new PlainConnectionSocketFactory())  
                                .build();
                        
                        //创建连接管理器 
                        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);  
                        cm.setMaxTotal(MAX_CONNECTION_NUM);  
                        cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);

                        // 创建httpClient对象  
                        httpClient = HttpClients.custom()
                                .setConnectionManager(cm)
                                .setDefaultRequestConfig(REQUEST_CONFIG)
                                .build();
                        
                    } catch (Exception e) {
                    	logger.error(methodName+" init PoolingHttpClientConnectionManager Error" , e);  
                    }
                }
            }
        }
        logger.debug(methodName, "initPoolManager");  
        
	}
	
	 /** 
     * 创建SSL连接 
     * @throws Exception  
     */  
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() throws Exception{  
        // 创建TrustManager   
        X509TrustManager xtm = new X509TrustManager(){
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}   
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}   
            public X509Certificate[] getAcceptedIssuers() { return null; }   
        };   
        
        // TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext   
        SSLContext ctx = SSLContext.getInstance("TLS");   
        // 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用   
        ctx.init(null, new TrustManager[]{xtm}, null);   
        SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(ctx);  
        return sslsf;  
    }

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient(RequestConfig config) {
		init();
		return HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm).build();
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	public static String get(String url, Map<String, Object> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder(new URI(url));

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.addParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		return getResult(httpGet);
	}

	public static String get(String url,
			Map<String, Object> headers, Map<String, Object> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder(new URI(url));

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.addParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}
		return getResult(httpGet);
	}

	public static String post(String url) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}

	public static String post(String url, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHAR_SET));
		return getResult(httpPost);
	}

	public static String post(String url,
			Map<String, Object> headers, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpPost
					.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHAR_SET));

		return getResult(httpPost);
	}

	private static ArrayList<NameValuePair> covertParams2NVPS(
			Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), String
					.valueOf(param.getValue())));
		}

		return pairs;
	}

	/**
	 * 处理Http请求
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request){
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		CloseableHttpResponse response =null; 
		try {
			response = httpClient.execute(request);
			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			Header header  = entity.getContentEncoding();
			if (entity != null) {
				result = EntityUtils.toString(entity,CHAR_SET);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally{
			//释放连接  
            if(response != null) {   
                try {
                    EntityUtils.consume(response.getEntity());//会自动释放连接  
                    response.close();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }   
            }  
		}
		return result;
	}
}
