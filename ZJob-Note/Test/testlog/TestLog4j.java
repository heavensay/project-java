package testlog;

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

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import testlog.log4j.Bean1;
import testlog.log4j.level2.Level2Bean;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestLog4j{
	
	@Before
	public void before(){
		//初始化log4j日志系统
		PropertyConfigurator.configure("bin/testlog/log4j.properties");
	}
	
	
	@Test
	public void test1Simplest() throws Exception{
		Bean1 bean1 = new Bean1();
		bean1.f1();
	}
	
	@Test
	public void test2Level2() throws Exception{
		Level2Bean l2 = new Level2Bean();
		l2.f1();
	}	

}
