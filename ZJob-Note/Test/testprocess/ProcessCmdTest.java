package testprocess;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessCmdTest {
 
	private static Logger logger = LoggerFactory.getLogger(ProcessCmdTest.class);
	
	private static String scriptPath = "C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/";
	
	
	/**
	 * 测试process关闭有效性
	 * sleep.bat运行需要10秒，中间直接destory，发现cmd进程马上结束，但是其中的ping进程还存在。
	 */   
	@Test
	public void destoryTest(){
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		try {
			
			logger.debug("begin："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//cmd 5秒没执行完成，直接退出
			Thread.sleep(5000L);
			logger.debug("process.destory");
			process.destroy();
			logger.debug("return waitfor:"+process.waitFor());
			
			Thread.sleep(20000L);
			logger.debug("step5： main thread process end");
		} catch (Exception e) {
			logger.error(null,e);
		}
	}
	
	/**
	 * 
	 * start-sleep.bat->sleep.bat
	 * note:sleep.bat是以start方式运行，会开启一个新的进程；
	 * result:1 start sleep.bat:马上执行完成，退出进程；sleep.bat进程还会继续运行；两个脚本各自启动一个进程
	 *        2 call sleep.bat:马上执行完成，退出进程；sleep.bat没有执行完；两个脚本公用一个进程
	 *        
	 */   
	@Test
	public void destoryStartBatTest(){
		String commandPath = scriptPath +  "start-sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		try {
			
			logger.debug("begin："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//cmd 5秒没执行完成，直接退出
			Thread.sleep(5000L);
			
			logger.debug("process.destory");
			process.destroy();
			logger.debug("return waitfor:"+process.waitFor());
			
			Thread.sleep(20000L);
			logger.debug("step5： main thread process end");
		} catch (Exception e) {
			logger.error(null,e);
		}
	}
	
	/**
	 * process 标准流、错误流读取
	 * 由于这两个流read会造成阻塞，所以要另开线程进行读取
	 */
	@Test
	public void ioReadTest(){
		String commandPath = scriptPath +  "start-sleep.bat";
//		StringBuffer command =  new StringBuffer("cmd /c "+commandPath +" > "+scriptPath+"iolog.txt");
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		final BufferedReader  errorReader ;
		final BufferedReader normalReader;
		try {
			
			logger.debug("step1："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//错误信息流打印
			errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(),"gbk"));
			
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						String line;
						StringBuffer errorLines = new StringBuffer();
						logger.debug("step2 errorReader read");
						while ( (line = errorReader.readLine())!=null) {
							errorLines.append(line).append("\n");
						}
						if(errorLines.length()>0){
							logger.debug("step2.1 errorReader content："+errorLines.substring(0,errorLines.length()-1));				
						}
						logger.debug("step2.2 errorReader end");
					}catch(Exception e){
						logger.error(null,e);
					}finally{
						try {
							errorReader.close();
						} catch (Exception e) {
							logger.error(null,e);
						}
					}
				}
			});
			
			t1.start();
			
			//正常信息流打印
			
			normalReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						String line;
						StringBuffer normalLines = new StringBuffer();
						logger.debug("step3 normalReader read");
						while ( (line = normalReader.readLine())!=null) {
							normalLines.append(line).append("\n");
						}
						if(normalLines.length()>0){
							logger.debug("step3.1 normalReader content："+normalLines.substring(0,normalLines.length()-1));				
						}
						logger.debug("step3.2 normalReader end");
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						try {
							normalReader.close();
						} catch (Exception e) {
							logger.error(null,e);
						}
					}
				}
			});
			
			t2.start();

			//cmd 5秒没执行完成，直接退出
			Thread.sleep(20000L);
			logger.debug("step5： main thread process end");
		} catch (Exception e) {
			logger.error(null,e);
		}
	}

	@Test
	public void closeProcess(){
		String commandPath = scriptPath +  "start-sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		final BufferedReader  errorReader ;
		final BufferedReader normalReader;
		try {
			
			logger.debug("step1："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(),"gbk"));
			normalReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));

			Thread.sleep(1000L);
//			process.destroy();
			errorReader.close();
			normalReader.close();
			
			//cmd 5秒没执行完成，直接退出
			Thread.sleep(15000L);
			logger.debug("step5： main thread process end");
		} catch (Exception e) {
			logger.error(null,e);
		}
	}
	
	/**
	 * process 输入流中断测试
	 * Thread.interrupt()中断不了normalreader.read()
	 * inputstream.close方法也会阻塞，不能中断normalreader.read()
	 * process.destory方法也不能中断normalreader.read()
	 * 目前来看没有别的办法来中断此种io阻塞
	 * 
	 */
	@Test
	public void interruptedIOTest(){
		String commandPath = scriptPath +  "start-sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		final BufferedReader normalReader ;
		final InputStreamReader inputStreamReader ;
		final InputStream inputStream ;
		
		try {
			
			logger.debug("step1："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//正常信息流打印
			inputStream = process.getInputStream();
			inputStreamReader = new InputStreamReader(process.getInputStream());
			normalReader = new BufferedReader(inputStreamReader);
			
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						String line;
						StringBuffer normalLines = new StringBuffer();
						logger.debug("step3 normalReader read");
						while ( (line = normalReader.readLine())!=null) {
							normalLines.append(line).append("\n");
						}
						if(normalLines.length()>0){
							logger.debug("step3.1 normalReader content："+normalLines.substring(0,normalLines.length()-1));				
						}
						logger.debug("step3.2 normalReader end");
					}catch(Exception e){
						logger.error(null,e);
					}finally{
						try {
							normalReader.close();
							inputStreamReader.close();
							inputStream.close();
						} catch (Exception e) {
							logger.error(null,e);
						}
					}
				}
			});
			
			t2.start();
			Thread.sleep(1000L);
			logger.debug("t2.interrupt()");
			t2.interrupt(); //normalreader.read()中断不了
			
			Thread.sleep(1000L);
			
//			process.destroy();	//也中断不了normalreader.read()阻塞
			
			logger.debug("normalReader.close()");
//			normalReader.close();
//			logger.debug("inputStreamReader.close()");
//			inputStreamReader.close();
			logger.debug("inputStream.close()"); 

			inputStream.close(); //无效，close也被阻塞
			logger.debug(" in stream close 完毕");
//			process.getInputStream().close();

			
			//cmd 5秒没执行完成，直接退出
			Thread.sleep(20000L);
			logger.debug("step5： main thread process end");
		} catch (Exception e) {
			logger.error(null,e);
		}
	}
	
	/**
	 * Process.exitValue()测试
	 * result：采用非阻塞的方式返回，如果没有立即拿到返回值，则抛出异常IllegalThreadStateException
	 * @throws Exception
	 */
	@Test
	public void exitValueTest() throws Exception{
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		Process process = Runtime.getRuntime().exec(command.toString());
	
		for(int i=0;i<=5;i++) {
			Thread.sleep(2000L);
			try{
				process.exitValue();
				logger.debug("exitValue:{}",process.exitValue());
				process.destroy();
				logger.debug("after destroy exitValue:{}",process.exitValue());
				break;
			}catch(IllegalThreadStateException e){
				logger.error("process 还没执行完成，无法获取exitvalue,有IllegalThreadStateException异常");
			}
		}
		logger.debug("执行完毕");
	}
	
	
	/**
	 * start-sleep.sh->sleep.sh会睡眠20秒
	 * t1会卡住，一直到sleep.sh执行完为止。其他步骤都是直接完成。
	 * 这样不合理，本身start-sleep进程不会阻塞，直接运行完毕。可能在于start-sleep进程关闭，但是jvm的errorstream流还一直保持着
	 * 使用ProcessBuilder，就不会有上述问题。
	 */
	public static void mulThreadReadProcessTest(){
		StringBuffer command =  new StringBuffer("/bin/sh ");
		command.append(" start-sleep.sh ");
		final BufferedReader  errorReader ;
		final BufferedReader normalReader;
		try {
			
			logger.debug("step1："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//错误信息流打印
			errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			Thread t1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						String line;
						StringBuffer errorLines = new StringBuffer();
						while ( (line = errorReader.readLine())!=null) {
							errorLines.append(line).append("\n");
						}
						if(errorLines.length()>0){
							logger.debug("step2："+errorLines.substring(0,errorLines.length()-1));				
						}
						logger.debug("step2.1 errorReader end");
						errorReader.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			
			t1.start();
			
			//正常信息流打印
			
			normalReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						String line;
						StringBuffer normalLines = new StringBuffer();
						while ( (line = normalReader.readLine())!=null) {
							normalLines.append(line).append("\n");
						}
						if(normalLines.length()>0){
							logger.debug("step3："+normalLines.substring(0,normalLines.length()-1));				
						}
						logger.debug("step3.1 normalReader end");
						normalReader.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			
			t2.start();

			int signCode = process.waitFor();
			logger.debug("step4："+signCode);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ProcessBuilder来构建执行cmd
	 * 1合并正常流和错误流
	 * @throws IOException
	 */
	public static void processBuilderTest() throws Exception{
		ProcessBuilder builder = new ProcessBuilder("/bin/sh","start-sleep.sh");//cmd: /bin/sh  start-sleep.sh
		builder.redirectErrorStream(true);
		
		builder.directory(new File("/data/shell"));
		Process process = builder.start();
		
		final BufferedReader inputReader;
		inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					String line;
					StringBuffer normalLines = new StringBuffer();
					while ( (line = inputReader.readLine())!=null) {
						normalLines.append(line).append("\n");
					}
					if(normalLines.length()>0){
						logger.debug("step3："+normalLines.substring(0,normalLines.length()-1));				
					}
					logger.debug("step3.1 normal+error Reader end");
					inputReader.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		t2.start();
		
		int signCode = process.waitFor();
		logger.debug("step4："+signCode);
	}
	
}
