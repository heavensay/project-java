package test.jmx.openMBean;

import javax.management.JMX;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.jmx.mbeanserver.DefaultMXBeanMappingFactory;
import com.sun.jmx.mbeanserver.MXBeanMapping;
import com.sun.jmx.mbeanserver.MXBeanMappingFactory;

public class Client {
    public static void main(String[] args) throws Exception {
        //构造一个Rmi-Connector
        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://localhost:9999/server");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection msc = jmxc.getMBeanServerConnection();

        ObjectName name = new ObjectName("ZooOpenMBean:type=OpenMBean");

        MBeanInfo info = msc.getMBeanInfo(name);

        String zooName = (String) msc.getAttribute(name, "zooName");
        //tigerCount初始化的时候就有一只
        int tigerCount = (Integer) msc.getAttribute(name, "tigerCount");
        //这里面就是得到了一个CompositeData，代表了一个Tiger
        Object tiger = msc.getAttribute(name, "tiger");
        Object animalNames = msc.getAttribute(name, "animalNames");

        System.out.println(tigerCount);//output：1，初始化的时候就为1
        System.out.println(zooName);
        System.out.println(tiger);
        System.out.println(animalNames);

        CompositeData data = builtCompositeData();
        msc.invoke(name, "addTiger", new Object[]{data}, new String[]{CompositeData.class.getName()});
        int tigerCount2 = (Integer) msc.getAttribute(name, "tigerCount");
        System.out.println(tigerCount2);//output:2 ，说明已经新增了一只Tiger
    }

    /**
     * @return CompositeData 是一个Open value， 代表了Tiger实例
     * @throws Exception
     */
    public static CompositeData builtCompositeData() throws OpenDataException {
        //这个注释代码构造的一个CompositeData，代表了Tiger
        //提交到Server端的时候，会转化为Tiger实例
        CompositeType ct = new CompositeType("test.jmx.mxbean.Tiger",
                " tiger---", new String[]{"name", "foodNames"},
                new String[]{"-name-", "-foodNames-"},
                new OpenType[]{SimpleType.STRING, new ArrayType(SimpleType.STRING, false)});

        CompositeData ctV = new CompositeDataSupport(ct,
                new String[]{"name", "foodNames"}, new Object[]{"tiger2", new String[]{"food1", "food2", "food3"}});
        return ctV;

    }

}
