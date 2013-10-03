package com.cxf.service;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;


public class HelloTest {

	public static void main(String[] args) {
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();

		// Web服务的地址

		factoryBean.setAddress("http://localhost:8081/hello");

		// Web服务对象调用接口

		factoryBean.setServiceClass(HelloWorldImpl.class);

		Server server = factoryBean.create();

		server.start();

	}

}
