package testmq.rabbitmq.item;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import testmq.rabbitmq.RabbitMQUtil;

public class Consumer {

    public static void main(String[] args) throws Exception{
        String QUEUE_NAME = "queue-test-1";

        //创建一个新的连接
        Connection connection = RabbitMQUtil.getConnection();

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
}
