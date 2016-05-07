import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myhexin.util.PropertiesUtil;


public class Test1 {

	@Test
	public void test1(){
		System.out.println(StringEscapeUtils.escapeHtml("<123>"));
		System.out.println(StringEscapeUtils.escapeHtml("中国"));
		
		System.out.println(StringEscapeUtils.escapeJavaScript("中国"));
		System.out.println(StringEscapeUtils.escapeJavaScript("<script></script>"));
	}
	@Test
	public void test2(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("asdf");
		
		System.out.println(buffer.toString());
	}
	
	@Test
	public void test3() {

		String regx = ".*window\\.location\\s*=.*|.*style\\s*=.*x:ex.*pression\\s*\\(.*\\).*|.*document\\.cookie.*|.*eval\\s*\\(.*\\).*|.*unescape\\s*\\(.*\\).*|.*execscript\\s*\\(.*\\).*|.*msgbox\\s*\\(.*\\).*|.*confirm\\s*\\(.*\\).*|.*prompt\\s*\\(.*\\).*|.*<script>.*</script>.*|[[.&[^a]]|[|a|\n|\r\n|\r|\u0085|\u2028|\u2029]]*<script>.*</script>[[.&[^a]]|[|a|\n|\r\n|\r|\u0085|\u2028|\u2029]]*";
		
		String origFileName = "<script>333</2ScripT>";
//		String[] denys = { "jpg", "txt" };
//		String deny = denys[0];
		String deny = PropertiesUtil.getPropertyValue("file.filter.regx");
		
		int a = origFileName.indexOf(".", -1);
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		System.out.println(regx);
		System.out.println(pattern.matcher(origFileName).matches());

	}
}
