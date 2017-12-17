/**
 * Netflix hystrix官方例子hystrix-examples中的类，修改一下用于测试
 * 
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandFallback extends HystrixCommand<String> {

	private static Logger logger = LoggerFactory.getLogger(CommandFallback.class);
	
    private  Integer id = 0;

    public CommandFallback(Integer id) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.id = id;
    }

    @Override
    protected String run() throws InterruptedException {
    	if(id%2 == 0 ){
    		throw new RuntimeException(" invoke remote service fail ");
    	}else{
    		return "invoke remote service success,id="+id;
    	}
    }
    
    //服务降级
    @Override
    protected String getFallback() {
    	// TODO Auto-generated method stub
    	return "fallback,id="+id;
    }

}
