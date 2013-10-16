package test.jmx.mxbean.simple;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class Server {
	public static void main(String args[]) throws Exception{
		
//		MBeanServer mbs = MBeanServerFactory.createMBeanServer();
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		LocateRegistry.createRegistry(9999);
		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://localhost:9999/server");
		JMXConnectorServer cs = JMXConnectorServerFactory
				.newJMXConnectorServer(url, null, mbs);
		
		ZooMXBean mxbean = new ZooImpl();
		ObjectName name = new ObjectName("ZooMXBean:type=MXBean");
		//注册ZooOpenMBean这个OpenMBean
		mbs.registerMBean(mxbean, name);
		//开起RMI服务
		cs.start();
		
		System.out.println(" the mxbean server is start...");
	}
}
