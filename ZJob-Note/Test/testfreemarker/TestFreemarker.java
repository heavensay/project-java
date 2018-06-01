package testfreemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.*;

import freemarker.cache.ClassTemplateLoader;
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

	@Test
	public void test3() throws Exception{
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");

		File templatePath = new File("D:/work/yinhuotong/orig-2");
		System.out.println(templatePath.getAbsolutePath());
		cfg.setDirectoryForTemplateLoading(templatePath);
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		/* 获取模板文件 */
		Template template = cfg.getTemplate("multiple_installments_for_loan_yht.ftl");

		/* 生成输出到文件 */
		File fileDir = new File("D:/work/yinhuotong/orig-2/t-index3.html");
		// 指定生成输出的文件
		Writer writer = new FileWriter(fileDir);

		HashMap params = new HashMap();
		params.put("application_id","888777");

		HashMap map = new HashMap();
		map.put("model",params);
		template.process(map, writer);
		writer.flush();
	}


	@Test
	public void testFormat() throws Exception{
		//创建一个模版对象
		Template t = new Template(null, new StringReader("用户名：${user};time:${(ctime?date?string('yyyy年MM月dd日'))!}," +
				" 金额：${(money?string.currency)!\"￥___.00\"}"), null);
		//创建插值的Map
		Map map = new HashMap();
		map.put("user", "lavasoft");
		map.put("ctime", new Date());
//		map.put("money", 200.53);
		map.put("money", new BigDecimal("200.11"));
		//执行插值，并输出到指定的输出流中
		t.process(map, new OutputStreamWriter(System.out));
	}

	/**
	 * list测试
	 * List指令还隐含了两个循环变量：
	 *    item_index:当前迭代项在所有迭代项中的位置，是数字值。
	 *    item_has_next:用于判断当前迭代项是否是所有迭代项中的最后一项。
	 *
	 *在循环过程中，如果您想跳出循环，那么可以使用结合break指令，即<#break>来完成。
	 *
	 * @throws Exception
	 */
	@Test
	public void testList() throws Exception{
		String html = "      <#if nos?? >\n" +
				"                <#list nos as no>\n" +
				"${no}\n" +
				"                </#list>\n" +
				"            </#if> " ;

		String loopHtml = "      <#if nos?? >\n" +
				"                <#list nos as no>\n" +
				" <#if no_index==0> \n"+
				"   ${no}\n" +
				"<#break>\n"+
				" </#if> \n"+
				"                </#list>\n" +
				"            </#if> " ;

		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		Map map = new HashMap();
		map.put("nos",list);

		Template t = new Template(null,html,null);
		Template t2 = new Template(null,loopHtml,null);
		t.process(map,new OutputStreamWriter(System.out));
		t2.process(map,new OutputStreamWriter(System.out));
	}

	/**
	 * 逻辑运算
	 * @throws Exception
	 */
	@Test
	public void testLogicalCalc() throws Exception{
		String html = " ${num1 + num2}";
		Map map = new HashMap();
		map.put("num1",4);
		map.put("num2",5);
//		map.put("num2","5");
		Template t = new Template(null,html,null);
		t.process(map,new OutputStreamWriter(System.out));
	}

	/**
	 * 逻辑运算
	 * @throws Exception
	 */
	@Test
	public void testAssign() throws Exception{
		String html = "  <#assign customnum> ${num1}</#assign> \n"+
				"<br>${customnum}+${num2}</br>";//${customnum?''}
		Map map = new HashMap();
		map.put("num1",4);
		map.put("num2",5);
//		map.put("num2","5");
		Template t = new Template(null,html,null);
		t.process(map,new OutputStreamWriter(System.out));
	}

	/**
	 * 如何assign一个object(目前没试验成功)
	 * 赋值一个object失败，一致都是变为string
	 * @throws Exception
	 */
	@Test
	public void testAssignObject() throws Exception{
		String html = "      <#if users?? >\n" +
				"                <#list users as user>\n" +
									"<br>==========+${user.id}+${user.name}</br> \n"+
					"                <#if user_index==0>\n" +
						"                <#assign firstUser>${user}</#assign>\n" + //失败，firstUser为string类型了
						"                <#assign firstId>${user.id}</#assign>\n" +
						"                <#assign firstName>${user.name}</#assign>\n" +
						"                <#break>\n" +
					"                </#if>\n" +
				"                </#list>\n" +
				"            </#if> "+
//				"<br>${firstUser.id}</br>\n"+
				"<br>${firstId+firstId}</br>\n"+
				"<br>${firstName}</br>";

		User user1 = new User();
		user1.setId(1);
		user1.setName("tom");

		User user2 = new User();
		user2.setId(1);
		user2.setName("Jerry");
		List list =new ArrayList();
		list.add(user1);
		list.add(user2);

		Map map = new HashMap();
		map.put("users",list);
		Template t = new Template(null,html,null);
		t.process(map,new OutputStreamWriter(System.out));
	}

}
