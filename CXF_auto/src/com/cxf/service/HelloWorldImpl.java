package com.cxf.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.jws.WebService;

//serviceName与portName属性指明WSDL中的名称, endpointInterface属性指向Interface定义类.
@WebService/*(serviceName = "InfoService", portName = "InfoServicePort", endpointInterface = "com.cxf.server.HelloWorld", 
		targetNamespace = "123.com")*/
public class HelloWorldImpl implements HelloWorld {

	public String sayHello(String username) {

		System.out.println("say hello is called!");

		return "Hello " + username;

	}

	public void annexTransportation(Annex annex) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(annex.getName());
		
		DataHandler annexData = annex.getAnnexData();
		InputStream is = annexData.getInputStream();
		
		FileOutputStream fos  = new FileOutputStream(new File("D:/kk/t.jpg"));
		
		byte[] b = new byte[1024];
		int count = 0 ;
		
		while ((count=is.read(b))!=-1) {
			fos.write(b,0,count);
		}
		
		fos.flush();
		fos.close();
		is.close();
		
	}

}