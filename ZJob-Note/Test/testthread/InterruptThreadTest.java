package testthread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptThreadTest {
	
	private static Logger logger = LoggerFactory.getLogger(InterruptThreadTest.class);

	public static void main(String[] args){
		
	}
	
	public void bioInterrputerTest(){
		Thread t1 = new Thread(new IOReaderTask());
		logger.debug(" user thread start ");
		t1.start();
		logger.debug(" main thread process end ");
	}
	
}

/**
 * io read阻塞
 * @author banana
 *
 */
class IOReaderTask implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(IOReaderTask.class);

	@Override
	public void run() {
		try{
			logger.debug("socket build begin ");
			Socket socket = new Socket("61.135.169.121", 80); //baidu
			InputStream inputStream = socket.getInputStream();
			
			logger.debug("socket build end ");
			
			byte[] bs = new byte[1024];
			int num = -1;
			while( (num = inputStream.read(bs)) > 0 ){
				System.out.println("read num:"+num);
				logger.debug("read num:"+num);
				logger.debug(new String(bs,0,num));
			}
			
			logger.debug("inputstream read over");
			inputStream.close();
			socket.close();
		}catch(IOException e){
			logger.debug("IOReaderTask Exception",e);
		}finally{
			logger.debug("finally");
		}
	}
}