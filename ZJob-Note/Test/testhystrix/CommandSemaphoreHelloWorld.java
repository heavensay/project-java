/**
 * Netflix hystrix官方例子hystrix-examples中的类，修改一下用于测试信号隔离
 * 
 * 信号隔离：服务调用在同一个线程中
 * 
 */
package testhystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandSemaphoreHelloWorld extends HystrixCommand<String> {

	private static Logger logger = LoggerFactory.getLogger(CommandSemaphoreHelloWorld.class);
	
    private final String name;

    public CommandSemaphoreHelloWorld(String name) {
	   super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
               // since we're doing work in the run() method that doesn't involve network traffic
               // and executes very fast with low risk we choose SEMAPHORE isolation
               .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
               .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
    	logger.debug("执行的线程：name：{},hash:{}",Thread.currentThread().getName(),Thread.currentThread().hashCode());
    	
        return "Hello " + name + "!";
    }

}
