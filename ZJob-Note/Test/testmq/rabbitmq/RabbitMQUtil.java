package testmq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {

    //创建连接工厂
    private static ConnectionFactory factory = null;

    static {
        factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("127.0.0.1");
        factory.setUsername("tt");
        factory.setPassword("tt");
        factory.setPort(5672);
    }


    public static Connection getConnection() {
        //创建一个新的连接
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

}
