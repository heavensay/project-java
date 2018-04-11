package testmq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {
	
    //创建连接工厂
    private static ConnectionFactory factory = null;
	
    static{
    	factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("127.0.0.1");
        factory.setUsername("banana");
        factory.setPassword("banana");
        factory.setPort(5672);
    }
    
	
	public void init(){
        //创建一个新的连接
//        Connection connection = factory.newConnection();
        
        
	}
	
}
