
package com.cxf.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.8
 * 2013-06-17T17:31:57.613+08:00
 * Generated source version: 2.6.8
 */
public final class HelloWorldImpl_HelloWorldImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://service.cxf.com/", "HelloWorldImplService");

    private HelloWorldImpl_HelloWorldImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = HelloWorldImplService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        HelloWorldImplService ss = new HelloWorldImplService(wsdlURL, SERVICE_NAME);
        HelloWorldImpl port = ss.getHelloWorldImplPort();

        {
            System.out.println("Invoking sayHello...");
            java.lang.String _sayHello_arg0 = "";
            java.lang.String _sayHello__return = port.sayHello(_sayHello_arg0);
            System.out.println("sayHello.result=" + _sayHello__return);


        }
        {
            System.out.println("Invoking annexTransportation...");
            com.cxf.client.Annex _annexTransportation_arg0 = null;
            port.annexTransportation(_annexTransportation_arg0);


        }

        System.exit(0);
    }

}
