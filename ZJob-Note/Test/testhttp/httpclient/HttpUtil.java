package testhttp.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.apache.http.entity.ContentType;
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
	
	/**
	 * request&response内容默认的字符编码
	 */
	private static String DEFAULT_CHAR_SET = "UTF-8";

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
		return format2String(httpGet);
	}

	public static String get(String url, Map<String, Object> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder(new URI(url));

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.addParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		return format2String(httpGet);
	}

	public static String get(String url,
			Map<String, Object> headers, Map<String, Object> params)
			{
		URIBuilder ub = new URIBuilder(new URI(url));

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.addParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}
		return format2String(httpGet);
	}

	public static String post(String url) {
		HttpPost httpPost = new HttpPost(url);
		return format2String(httpPost);
	}

	public static String post(String url, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, DEFAULT_CHAR_SET));
		return format2String(httpPost);
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
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, DEFAULT_CHAR_SET));

		return format2String(httpPost);
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
	 * 以String类型获取reposne内容
	 * 
	 * @param request
	 * @return String 
	 */
	private static String format2String(HttpRequestBase request){
		String result = null;
		CloseableHttpResponse response =null; 
		try {
			response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				
				/**
				 * 智能编码获取网页内容
				 * 1优先使用头信息header content-type  
				 * 2使用html内容中mta标签编码，meta:content-type(<meta http-equiv=content-type content=text/html;charset=utf-8>)
				 */
				final ContentType contentType = ContentType.get(entity);
                if (contentType != null && contentType.getCharset() != null) {
                  	result = EntityUtils.toString(entity);
                }else{
                	byte[] bytes = EntityUtils.toByteArray(entity);
                	if(bytes!=null && bytes.length>0){
                		String metaCharset = findChartsetByBody(bytes);
                		result = new String(bytes,metaCharset != null?metaCharset:DEFAULT_CHAR_SET);
                	}
                }
                
//				/**
//				 * 1优先使用header:content-type.charset来编码返回的html
//				 * 2默认使用DEFAULT_CHAR_SET来编码返回的html
//				 */
//                result = EntityUtils.toString(entity,DEFAULT_CHAR_SET);
                
    			EntityUtils.consume(entity);
			}

		} catch (IOException e) {
			System.out.println(e);
		} finally{
			close(response);
		}
		return result;
	}
	
	
	/**
	 * 尝试关闭response
	 * 
	 * @param resp HttpResponse对象
	 */
	private static void close(CloseableHttpResponse resp) {
		try {
			if(resp == null) return;
			
			EntityUtils.consume(resp.getEntity());//会自动释放连接  
			resp.close();
		} catch (IOException e) {
			logger.error("aa",e);
		}
	}
	
	/**
	 * 在html meta标签中获取服务端返回给客户端使用的字符编码：
	 * www.baidu.com返回的内容字符编码在meta标签中. <meta http-equiv=content-type content=text/html;charset=utf-8>
	 * @param htmlBytes html内容byte形式
	 * @return
	 */
	private static String findChartsetByBody(byte[] htmlBytes) {
		String html = new String(htmlBytes);// 默认编码转成字符串，因为我们的匹配中无中文，所以串中可能的乱码对我们没有影响
		String charset = null;
		String regEx = "<meta.*?charset=([[a-z]|[A-Z]|[0-9]|-]*)>";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		System.out.println(html);
		Matcher m = p.matcher(html);
		if (m.groupCount() > 0) {
			m.find();
			charset = m.group(1);
		}
		return charset;
	}
}
