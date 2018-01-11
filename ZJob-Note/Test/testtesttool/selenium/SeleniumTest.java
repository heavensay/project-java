package testtesttool.selenium;

import java.sql.Driver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {
	
	/**
	 * java selenium运行测试
	 * 代码参考:http://blog.sina.com.cn/s/blog_9d0d0e1b0102wsbp.html
	 */
	@Test
	public void testChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		
		
		ChromeOptions options = new ChromeOptions();
		
		//去掉chrome的 "...--ignore-certificate-error"错误提示
		options.addArguments("--test-type", "--ignore-certificate-errors");  
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.baidu.com");
		
		//定位到百度输入框并输入搜索关键字
		WebElement searchText = driver.findElement(By.id("kw"));
		searchText.sendKeys("Java Selenium");
		searchText.submit();
//		driver.quit();//退出
	}
	
	
}
