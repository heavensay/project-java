package testprocess;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcessShTest {
 
	private static Logger logger = LoggerFactory.getLogger(ProcessUtil.class);
	
	
	public static void main(String[] args){
		ProcessShTest test = new ProcessShTest();
		test.mulThreadReadProcessTest();
	}
	
	/**
	 * start-sleep.sh(t1)->sleep.sh(t2，执行20秒)
	 * t1直接完成，t2继续执行
	 * 
	 * inputsteam,errorstream要等两个进程都结束之后，才会终结
	 */
	@Test
	public  void mulThreadReadProcessTest(){
		StringBuffer command =  new StringBuffer("/bin/sh ");
		command.append(" start-sleep.sh ");
		final BufferedReader  errorReader ;
		final BufferedReader normalReader;
		try {
			
			logger.debug("exec command:"+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//错误信息流打印
			errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			Thread errorReaderThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						String line;
						while ( (line = errorReader.readLine())!=null) {
							logger.debug("process error content:"+line);
						}
						logger.debug("process error read end");
					}catch(Exception e){
						logger.error(null,e);
					}finally{
						try {
							errorReader.close();
						} catch (IOException e) {
							logger.error(null,e);
						}
					}
				}
			});
			errorReaderThread.setDaemon(true);
			errorReaderThread.start();
			
			//正常信息流打印
			
			normalReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			Thread normalReaderThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						String line;
						while ( (line = normalReader.readLine())!=null) {
							logger.debug("process input content:"+line);
						}
						logger.debug("process input read end");
					}catch(Exception e){
						logger.error(null,e);
					}finally{
						try {
							normalReader.close();
						} catch (IOException e) {
							logger.error(null,e);
						}
					}
				}
			});
			normalReaderThread.setDaemon(true);
			normalReaderThread.start();
			
			logger.debug("wait");
			int exitValue = process.waitFor();
			logger.debug("exitValue:"+exitValue);
			process.destroy();
			Thread.sleep(20000L);
			
		} catch (Exception e) {
			logger.error(null,e);
		}
	}
	
	
	/**
	 * ProcessBuilder来构建执行cmd
	 * 1合并正常流和错误流
	 * @throws IOException
	 */
	@Test
	public void processBuilderTest() throws Exception{
		ProcessBuilder builder = new ProcessBuilder("/bin/sh","start-sleep.sh");//cmd: /bin/sh  start-sleep.sh
		builder.redirectErrorStream(true);
		
		builder.directory(new File("/data/shell"));
		Process process = builder.start();
		
		final BufferedReader inputReader;
		inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		Thread inputReaderThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					String line;
					while ( (line = inputReader.readLine())!=null) {
						logger.debug("process input content:"+line);
					}
					logger.debug("process input read end");
				}catch(Exception e){
					logger.error(null,e);
				}finally{
					try {
						inputReader.close();
					} catch (IOException e) {
						logger.error(null,e);
					}
				}
			}
		});
		inputReaderThread.setDaemon(true);
		inputReaderThread.start();
		
		int exitValue = process.waitFor();
		logger.debug("exitValue："+exitValue);
		Thread.sleep(2000L);
	}
	
}
