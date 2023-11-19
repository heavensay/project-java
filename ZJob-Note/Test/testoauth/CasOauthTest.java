package testoauth;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class CasOauthTest {

    //	redirect_uri = http://localhost:8088/redirect
//		token_uri = https:///www.cas-server.com:8443/cas/oauth2.0/accessToken
//		client_id = clientid
//		client_secret = clientSecret
//		authorize_url = https:///www.cas-server.com:8443/cas/oauth2.0/authorize
//		profile_url = https:///www.cas-server.com:8443/cas/oauth2.0/profile
    @Test
    public void test1() throws Exception {

        SSLContext sc = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                // TODO Auto-generated method stub
                return true;
            }
        }).build();
        //NoopHostnameVerifier  https//不校验域名
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                sc, NoopHostnameVerifier.INSTANCE);
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                .build();


//        String params = "response_type=code&client_id=client_id&redirect_uri=http://www.cas-client-oauth.com:8083/cas-client-oauth";
        String params = "response_type=code&client_id=client_id&redirect_uri=http://www.cas-client1.com:8081/cas-client1";
        HttpGet getReq = new HttpGet("https://www.cas-server.com:8443/cas/oauth2.0/authorize?" + params);

        HttpResponse response = httpClient.execute(getReq);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(" result:" + result);
    }

    @Test
    public void test2() throws Exception {

        SSLContext sc = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                // TODO Auto-generated method stub
                return true;
            }
        }).build();
        //NoopHostnameVerifier  https//不校验域名
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                sc, NoopHostnameVerifier.INSTANCE);
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                .build();


        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("rant_type", "authorization_code"));
        params.add(new BasicNameValuePair("client_id", "clientid"));
        params.add(new BasicNameValuePair("client_secret", "clientSecret"));
        params.add(new BasicNameValuePair("code", "code"));
        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8088/redirect"));

        HttpPost postReq = new HttpPost("https:///www.cas-server.com:8443/cas/oauth2.0/accessToken");
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
        postReq.setEntity(entity);

        HttpResponse response = httpClient.execute(postReq);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
//		System.out.println("url:"+url+"访问成功 result:"+result);
    }
}
