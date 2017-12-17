/**
 * Netflix hystrix官方例子hystrix-examples中的类，修改一下用于测试
 * 
 */
package testhystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandHelloWorld extends HystrixCommand<String> {

	private static Logger logger = LoggerFactory.getLogger(CommandHelloWorld.class);
	
    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
    	logger.debug("执行的线程：name：{},hash:{}",Thread.currentThread().getName(),Thread.currentThread().hashCode());
    	
    	//用于测试同步/异步
    	Thread.sleep(2000L);
        return "Hello " + name + "!";
    }

}
