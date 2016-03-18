package testfreemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;
import org.junit.runners.JUnit4;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreemarker{

	@Test
	public void test0Simplest() throws Exception{
        //创建一个模版对象 
		Template t = new Template(null, new StringReader("用户名：${user};URL：    ${url};姓名： 　${name}"), null); 
        //创建插值的Map 
        Map map = new HashMap(); 
        map.put("user", "lavasoft"); 
        map.put("url", "http://www.baidu.com/"); 
        map.put("name", "百度"); 
        //执行插值，并输出到指定的输出流中 
        t.process(map, new OutputStreamWriter(System.out));
	}
	
	@Test
	public void test1SimpleData() throws Exception{
		Configuration cfg = new Configuration();
		
		File templatePath = new File("bin/testfreemarker");
		System.out.println(templatePath.getAbsolutePath());
		cfg.setDirectoryForTemplateLoading(templatePath);
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		/* 获取模板文件 */
		Template template = cfg.getTemplate("1.ftl");

//		/* 模板数据 */
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("class", "Order");
//		Collection<Map<String, String>> properties = new HashSet<Map<String, String>>();
//		root.put("properties", properties);

		/* 字段1 orderId */
		Map rootModel = new HashMap();
		rootModel.put("name", "tom");
		rootModel.put("age", 18);

		/* 生成输出到控制台 */
		Writer out = new OutputStreamWriter(System.out);
		template.process(rootModel, out);
		out.flush();

		/* 生成输出到文件 */
		File fileDir = new File("1.rlt");
		// 指定生成输出的文件
		Writer writer = new FileWriter(fileDir);
		template.process(rootModel, writer);
		writer.close();
	}

}
