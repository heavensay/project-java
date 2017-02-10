package testprocess;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

public class ProcessTest {
 
	public static void main(String[] args) throws Exception{
		
		System.out.println("before:"+new Date());
//		f2();
		processBuilderTest();
		System.out.println("after :"+new Date());
	}
	
	public static void f1(){
		StringBuffer command =  new StringBuffer("/bin/sh ");
		command.append(" start-kaoqin-bc-job.sh ");
		command.append(" -b 2016-04-14 ");
		command.append(" -e 2016-04-14 ");
		try {
			
			System.out.println("begin exec collection attendance command："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
//			int signCode = process.exitValue();
			
//			System.out.println("collection attendance script sign return："+signCode);
//			Thread.sleep(1000);//sleep.sh 马上能执行完，
//			process.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
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
			
			System.out.println("step1："+command.toString());
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
							System.out.println("step2："+errorLines.substring(0,errorLines.length()-1));				
						}
						System.out.println("step2.1 errorReader end");
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
							System.out.println("step3："+normalLines.substring(0,normalLines.length()-1));				
						}
						System.out.println("step3.1 normalReader end");
						normalReader.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			
			t2.start();

			int signCode = process.waitFor();
			System.out.println("step4："+signCode);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void f3(){
		StringBuffer command =  new StringBuffer("/bin/sh ");
		command.append(" start-sleep.sh ");
		final BufferedReader  errorReader ;
		final BufferedReader normalReader;
		try {
			
			System.out.println("step1："+command.toString());
			Process process = Runtime.getRuntime().exec(command.toString());
			
			//错误信息流打印
			errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
					try{
						String line;
						StringBuffer errorLines = new StringBuffer();
						while ( (line = errorReader.readLine())!=null) {
							errorLines.append(line).append("\n");
						}
						if(errorLines.length()>0){
							System.out.println("step2："+errorLines.substring(0,errorLines.length()-1));				
						}
						errorReader.close();
					}catch(Exception e){
						e.printStackTrace();
					}
			
			
			//正常信息流打印
			
			normalReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					try{
						String line;
						StringBuffer normalLines = new StringBuffer();
						while ( (line = normalReader.readLine())!=null) {
							normalLines.append(line).append("\n");
						}
						if(normalLines.length()>0){
							System.out.println("step3："+normalLines.substring(0,normalLines.length()-1));				
						}
						normalReader.close();
					}catch(Exception e){
						e.printStackTrace();
					}

			int signCode = process.waitFor();
			System.out.println("step4："+signCode);
			
			
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
						System.out.println("step3："+normalLines.substring(0,normalLines.length()-1));				
					}
					System.out.println("step3.1 normal+error Reader end");
					inputReader.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		t2.start();
		
		int signCode = process.waitFor();
		System.out.println("step4："+signCode);
	}
	
	
	/**
	 * 终端bio阻塞进程关闭
	 */
	public void interrecptReadTest(){
		
		try{
			Socket socket = new Socket("61.135.169.121", 80);
			InputStream inputStream = socket.getInputStream();
			
			byte[] bs = new byte[1024];
			int num = -1;
			while( (num = inputStream.read(bs)) > 0 ){
				System.out.println("read num:"+num);
				System.out.println(new String(bs,0,num));
			}
			
			inputStream.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
