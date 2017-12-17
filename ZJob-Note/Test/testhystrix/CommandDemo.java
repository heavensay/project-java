/**
 * Netflix hystrix官方例子hystrix-examples中的类，修改一下用于测试
 * 
 */
package testhystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandDemo extends HystrixCommand<String> {

	String name ;
	
    
    public CommandDemo(String name,Integer poolSize,Integer queueSize) {
    	super(setter(poolSize,queueSize));
        this.name = name;
    }


    private static Setter setter(Integer poolSize,Integer queueSiz) { 
        //服务分组 
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("ExampleGroup"); 
        //服务标识 
        HystrixCommandKey commandKey =HystrixCommandKey.Factory.asKey("poolService"); 
        //线程池名称 
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("testPool"); 
//        //线程池配置 
//        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter() 
//               .withCoreSize(10) 
//               .withKeepAliveTimeMinutes(5) 
//               .withMaxQueueSize(Integer.MAX_VALUE) 
//               .withQueueSizeRejectionThreshold(10000); 
        //线程池配置 
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter() 
               .withCoreSize(poolSize) 
               .withMaxQueueSize(queueSiz);
//               .withQueueSizeRejectionThreshold(queueSiz);
  
        //命令属性配置 
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter() 
        	   .withExecutionTimeoutInMilliseconds(10000)
               .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD); 
        return HystrixCommand.Setter 
                        .withGroupKey(groupKey) 
                       .andCommandKey(commandKey) 
                       .andThreadPoolKey(threadPoolKey) 
                       .andThreadPoolPropertiesDefaults(threadPoolProperties) 
                        .andCommandPropertiesDefaults(commandProperties);
    }
    
    @Override
    protected String run() throws InterruptedException {
    	//用于测试同步/异步
    	Thread.sleep(2000L);
    	String s= "success: name="+name+" ;Thread:"+Thread.currentThread().getName()+"  "+Thread.currentThread().hashCode();
    	System.out.println(s);
        return s;
    }
    
    @Override
    protected String getFallback() {
    	String s= "fallback: name="+name+" ;Thread:"+Thread.currentThread().getName()+"  "+Thread.currentThread().hashCode();
		System.out.println(s);
        return s;
    }

}
