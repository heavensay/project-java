package test.jmx.mxbean.simple;


import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client {

    public static void main(String[] args) throws Exception {

        //构造一个Rmi-Connector
        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://localhost:9999/server");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection msc = jmxc.getMBeanServerConnection();

        ObjectName name = new ObjectName("ZooMXBean:type=MXBean");

        Object tiger = msc.getAttribute(name, "Tiger");
        if (tiger instanceof CompositeData) {
            System.out.println("返回的Tiger的类型为CompositeData");
            CompositeData data = (CompositeData) tiger;
            String nm = (String) (data.get("name"));
            String[] foods = (String[]) (data.get("foodNames"));
            System.out.println(" the tiger's name is :" + nm);
            System.out.println(" the tiger's foods is :" + foods);
        }

        Integer count1 = (Integer) msc.getAttribute(name, "TigerCount");
        System.out.println(" the amount of tiger is:" + count1);

        //构造一个CompositeData代表Tiger实例，用于addTiger(Tiger)的参数
        CompositeType ct2 = new CompositeType("test.jmx.mxbean.Tiger", " tiger---",
                new String[]{"name", "foodNames"},
                new String[]{"-name-", "-foods-"},
                new OpenType[]{SimpleType.STRING, new ArrayType(1, SimpleType.STRING)});

        CompositeData ct2V = new CompositeDataSupport(ct2,
                new String[]{"name", "foodNames"},
                new Object[]{"the second tiger", new String[]{"food1", "food2", "food3"}});

        Object returnValue = msc.invoke(name, "addTiger",
                new Object[]{ct2V},
                new String[]{CompositeData.class.getName()});
        //得到服务端Tiger的数量，新增了以后，应该是2只
        Integer count2 = (Integer) msc.getAttribute(name, "TigerCount");
        System.out.println(" after invoke addTiger(...),the amount of tiger is:" + count2);
    }
}
