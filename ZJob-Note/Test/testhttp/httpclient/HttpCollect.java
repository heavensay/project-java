package testhttp.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
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
	
	/**
	 * 基础测试，获取baidu网页信息
	 * @throws HttpException
	 * @throws IOException
	 */
	@Test
	public void test1CollectBaidu() throws HttpException, IOException {
		HttpClient httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器
		GetMethod getMethod = new GetMethod("http://www.baidu.com");// 创建一个get方法，类似在浏览器地址栏中输入一个地址
		int statusCode = httpclient.executeMethod(getMethod);// 回车——出拳！
		System.out.println("response=" + getMethod.getResponseBodyAsString());// 察看拳头命中情况，可以获得的东西还有很多，比如head,
		getMethod.releaseConnection();// 释放，记得收拳哦
	}
	
	@Test
	public void test2() throws HttpException, IOException {
		HttpClient httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器
		GetMethod getMethod = new GetMethod("http://115.29.166.220:8081/investws/user/login?mobilephone=15906644630&mobileAuthcode=111111");
		int statusCode = httpclient.executeMethod(getMethod);
		System.out.println("response=" + getMethod.getResponseBodyAsString());
		getMethod.releaseConnection();
	}
}
