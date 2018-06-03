package testmq.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

public class RabbitMQTest {

	String host = "127.0.0.1";
	String user = "guest";
	String pwd = "guest";
	Integer port = 5672;
	
	@Test
	public void test1Consumer() throws Exception {
		String QUEUE_NAME = "queue-test-1";
		 //创建连接工厂
		ConnectionFactory factory = null;

		factory = new ConnectionFactory();
		// 设置RabbitMQ相关信息
		factory.setHost(host);
		factory.setUsername(user);
		factory.setPassword(pwd);
		factory.setPort(port);
		
        //创建一个新的连接
        Connection connection = factory.newConnection();

        //创建一个通道
        Channel channel = connection.createChannel();

        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };

//        while(true){
			//自动回复队列应答 -- RabbitMQ中的消息确认机制
			channel.basicConsume(QUEUE_NAME, true, consumer);
//		}
		System.out.println("end========");
		System.in.read();
	}

}
