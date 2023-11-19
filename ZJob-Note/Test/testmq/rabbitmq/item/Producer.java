package testmq.rabbitmq.item;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import testmq.rabbitmq.RabbitMQUtil;

public class Producer {

    public static void main(String[] args) throws Exception {

        //创建一个新的连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建一个通道
        Channel channel = connection.createChannel();
//
        // 声明一个队列
//        channel.queueDeclare(Config.QUEUE, false, false, false, null);

        //发送消息到队列中
        String message = "Hello RabbitMQ:" + System.currentTimeMillis();
        //exhange，routingkey都不配置，则消息发送给rabbit server默认的exchange((AMQP default))
        channel.basicPublish(Config.EXCHANGE_NAME, Config.QUEUE, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send +'" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
