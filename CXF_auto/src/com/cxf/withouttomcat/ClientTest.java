package com.cxf.withouttomcat;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.cxf.service.Hello;
import com.cxf.service.HelloWorld;

public class ClientTest {
    public static void main(String[] trgs) {

        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();

        // 获取服务器端

        factoryBean.setAddress("http://localhost:8081/hello");

        // 通过客户端的接口获取服务器端的接口

        factoryBean.setServiceClass(Hello.class);

        Hello hello = (Hello) factoryBean.create();

        System.out.println(hello.sayHello("和谐dota"));

        factoryBean.setServiceClass(HelloWorld.class);

        HelloWorld helloWorld = (HelloWorld) factoryBean.create();
        System.out.println(hello.sayHello("helloworld 和谐dota"));
    }

}
