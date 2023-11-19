package test.jmx.openMBean;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

import test.jmx.HelloWorld;

public class OpenMBeanTest {

    public static void main(String[] args) throws Exception {
        ZooImpl zoo = new ZooImpl();
        ZooOpenMBean open = new ZooOpenMBean(zoo);
        ObjectName name = new ObjectName("ZooOpenMBean:type=OpenMBean--");

        MBeanServer ms = MBeanServerFactory.createMBeanServer();
        ms.registerMBean(open, name);

        String zooName = (String) ms.getAttribute(name, "zooName");
        Object tiger = ms.getAttribute(name, "tiger");
        System.out.println(zooName);
        System.out.println(tiger);
    }
}
