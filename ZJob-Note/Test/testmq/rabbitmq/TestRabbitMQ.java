package testmq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class TestRabbitMQ {

	String host = "127.0.0.1";
	String user = "guest";
	String pwd = "guest";
	Integer port = 5672;
	
	@Test
	public void test1Producer() throws Exception {
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
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //发送消息到队列中
        String message = "Hello RabbitMQ:"+System.currentTimeMillis();
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send +'" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
	}
	
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
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

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
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
