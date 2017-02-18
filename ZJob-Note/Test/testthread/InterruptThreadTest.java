package testthread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * socket io 阻塞线程中断测试
 * @author admin
 *
 */
public class InterruptThreadTest {
	
	private static Logger logger = LoggerFactory.getLogger(InterruptThreadTest.class);

	/**
	 * socket.io 阻塞线程中断测试
	 * @throws Exception
	 */
	@Test
	public void bioInterrputerTest() throws Exception{
		
		IOReaderTask ioTask = new IOReaderTask();
		
		Thread t1 = new Thread(ioTask);
		t1.start();
		logger.debug(" IOReaderTask  start ");
		
		//阻塞5秒
		Thread.sleep(5000L);
		
		logger.debug(" IOReaderTask {}  is alive:{} ",t1.getName(),t1.isAlive());
		if(t1.isAlive()){
			logger.debug(" IOReaderTask {}  close");
			ioTask.close();
			logger.debug(" IOReaderTask {}  is closed，now is alive:{} ",t1.getName(),t1.isAlive());	
		}
		
		logger.debug(" main thread process end ");
	}
	
	/**
	 * io流阻塞中断测试
	 * 
	 * result：thread.interuupte()和in.close()，方法都不能打断System.in阻塞
	 * 
	 * @throws Exception
	 */
	@Test
	public void ioInterrupt() throws Exception{
      Thread t = new Thread(new Runnable() {
            
            @Override
            public void run() {
                System.out.println("in");
                logger.debug("begin");
                try {
                    // IO阻塞
                    Integer i ;
                    while((i = System.in.read()) != -1){
                    	logger.debug("read:{}",i);
                    }
                } catch (IOException e) {
                    logger.error(null,e);
                } catch (Exception e) {
                	logger.error(null,e);
                }
                System.out.println("out");
                logger.debug("end");
            }
        });
        
        t.start();
        
        Thread.sleep(1000L);
        
//      t.interrupt();
        logger.debug("close开始");
        System.in.close();	//close阻塞组，不能打断线程t
        logger.debug("close结束");
        Thread.sleep(10000L);

       
	}
}	

/**
 * socket io read阻塞
 * @author banana
 *
 */
class IOReaderTask implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(IOReaderTask.class);

	Socket socket = null;
	InputStream inputStream = null;
	
	@Override
	public void run() {
		try{
			logger.debug("socket build begin ");
			socket = new Socket("61.135.169.121", 80); //baidu
			inputStream = socket.getInputStream();
			
			logger.debug("socket build end ");
			
			byte[] bs = new byte[1024];
			int num = -1;
			while( (num = inputStream.read(bs)) > 0 ){// 会一直阻塞在这里,不会被thread.interrupte打断,socket close会报异常，跳出阻塞
				System.out.println("read num:"+num);
				logger.debug("read num:"+num);
				logger.debug(new String(bs,0,num));
			}
			
			logger.debug("inputstream read over");
			inputStream.close();
			socket.close();
		}catch(IOException e){
			//throws java.net.SocketException: socket closed
			logger.debug("IOReaderTask 阻塞打断");
			logger.error("IOReaderTask Exception",e);
		}finally{
			logger.debug("finally");
		}
	}

	public void close(){
		try {
			//下面两种方式都会打断inputstrem.read阻塞,
			this.socket.close();
//			this.inputStream.close();
		} catch (IOException e) {
			logger.debug(" socket close failure",e);
		}
	}
}