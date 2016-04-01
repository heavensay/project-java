package testlog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.google.code.yanf4j.buffer.BufferDataException;

public class TestLogRedirector {

	String outPath = "bin/testlog/testlog-out.log";
	String errPath = "bin/testlog/testlog-err.log";
	
	@Before
	public void before() throws Exception {
		//系统输出流重定向
		System.setOut(new PrintStream(new FileOutputStream(new File(outPath))));
		System.setErr(new PrintStream(new FileOutputStream(new File(errPath))));	
	}
	
	@Test
	public void test1(){
		System.out.println("out="+System.currentTimeMillis()); //控制台没有输出，只写入到文件[outpath]下面了
		System.err.println("err="+System.currentTimeMillis()); //控制台没有输出，只写入到文件[errpath]下面了
	}
	
}
