package testprocess;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.commons.exec.Watchdog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 执行的脚本，尽量别费时，如果脚本也是开发人员编写，费时的脚本可以在后台运行;这样jvm不会被卡住
 * i.e.:linux shell： nohup command & ;  windows: start /b command 
 * 
 * @see apapche exec
 * @author admin
 *
 */
@Deprecated
public class ProcessUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessUtil.class);

	/**
	 * command执行的工具方法
	 * @param command 
	 * @param timeout 超时时间，超时之后结束process
	 * @param handlerResult 处理结果
	 */
	public static void exec(final String command,final Long timeout,final ProcessHandlerResult handlerResult){
		try {
			logger.debug("exec shell:"+command.toString());
			
			Thread processThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						innerExec(command, timeout, handlerResult);
					} catch (IOException e) {
						logger.error(null,e);
					}
				}
			});
			processThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void innerExec(String command,Long timeout,ProcessHandlerResult handlerResult) throws IOException{
		Process process = Runtime.getRuntime().exec(command);
		
		StreamHandlerTask inputStreamTask =  new StreamHandlerTask(process.getInputStream());
		StreamHandlerTask errorStreamTask = new StreamHandlerTask(process.getErrorStream());
		
		TimeoutTask timeoutTask = new TimeoutTask(timeout, process);
		
		Thread inputStreamThread = new Thread(inputStreamTask);
		Thread errorStreamThread = new Thread(errorStreamTask);
		Thread timeoutThread = new Thread(timeoutTask);
		
		timeoutThread.start();
		
		inputStreamThread.start();
		errorStreamThread.start();

		int exitValue;
		try {
			exitValue = process.waitFor();
			handlerResult.setHasResult(true);
			handlerResult.setExitCode(exitValue);
		} catch (InterruptedException e) {
			logger.error(null,e);
			process.destroy();
			Thread.currentThread().interrupt();
		}finally{
			stopProcess(process);
		}
		
	}
	
	/**
	 * ProcessBuilder来构建执行cmd
	 * 1合并正常流和错误流
	 * @throws IOException
	 */
	public static void execprocessBuilder() throws Exception{
		ProcessBuilder builder = new ProcessBuilder("/bin/sh","start-sleep.sh");//cmd: /bin/sh  start-sleep.sh
		builder.redirectErrorStream(true);
		
		builder.directory(new File("/data/shell"));
		Process process = builder.start();
		
		final BufferedReader inputReader;
		inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		Thread inputThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					String line;
					while ( (line = inputReader.readLine())!=null) {
						logger.debug("process input content:"+line);
					}
					logger.debug("process input reader end");
					
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
		inputThread.start();
		
		int signCode = process.waitFor();
	}
	
	private static class StreamHandlerTask implements Runnable {
		
		private InputStream input = null;
		
		public StreamHandlerTask(InputStream input){
			this.input = input;
		}
		
		@Override
		public void run() {
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
			try {
				String line;
				while ((line = inputReader.readLine()) != null) {
					logger.debug("process input content:" + line);
				}
				logger.debug("process input reader end");
			} catch (Exception e) {
				logger.error(null, e);
			} finally {
				try {
					inputReader.close();
				} catch (IOException e) {
					logger.error(null, e);
				}
			}
		}
	}
	
	/**
	 * 超时监视任务
	 * @author 
	 *
	 */
	private static class TimeoutTask implements Runnable {

		private Long timeout ;
		private Process process;
		
		public TimeoutTask(Long timeout,Process process) {
			this.timeout = timeout;
			this.process = process;
		}
		
		@Override
		public void run() {
			try {
				synchronized (this) {
					logger.debug("Process timeouttask begin:"+process.toString());
					this.wait(timeout);
					logger.debug("Process timeouttask end:"+process.toString());
					stopProcess(process);
					logger.debug("Process exit");
					process = null;
				}
			} catch (Exception e) {
				logger.debug(null,e);
				Thread.interrupted();
			}
			
		}
		
	}
	
	public static ProcessHandlerResult buildProcessHandlerResult(){
		return new ProcessHandlerResult();
	}
	
	public static void stopProcess(Process process){
		process.destroy();
		
        try {
            process.getInputStream().close();
        }
        catch (final IOException e) {
            logger.error(null,e);
        }

        try {
            process.getOutputStream().close();
        }
        catch (final IOException e) {
        	logger.error(null,e);
        }

        try {
            process.getErrorStream().close();
        }
        catch (final IOException e) {
        	logger.error(null,e);
        }
	}
}

 class ProcessHandlerResult{
	
	private boolean hasResult = false;
	public Integer exitCode = null;
	
	
	public boolean isHasResult() {
		return hasResult;
	}
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	public Integer getExitCode() {
		return exitCode;
	}
	public void setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
	}
	
}

