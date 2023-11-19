package test.jmx.openMBean;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class Server {
    public static void main(String args[]) throws Exception {

//		MBeanServer mbs = MBeanServerFactory.createMBeanServer();
        //PlatFormMBeanServer，就可以使用jconsole平台
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        LocateRegistry.createRegistry(9999);
        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://localhost:9999/server");
        JMXConnectorServer cs = JMXConnectorServerFactory
                .newJMXConnectorServer(url, null, mbs);

        ZooImpl zoo = new ZooImpl();
        ZooOpenMBean open = new ZooOpenMBean(zoo);
        ObjectName name = new ObjectName("ZooOpenMBean:type=OpenMBean");
        //注册ZooOpenMBean这个OpenMBean
        mbs.registerMBean(open, name);
        //开起RMI服务
        cs.start();

        System.out.println(" openmodel server is start...");
    }
}
