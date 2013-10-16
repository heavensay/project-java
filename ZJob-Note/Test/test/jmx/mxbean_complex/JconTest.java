package test.jmx.mxbean_complex;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.Attribute;
import javax.management.BadAttributeValueExpException;
import javax.management.BadBinaryOpValueExpException;
import javax.management.BadStringOperationException;
import javax.management.InvalidApplicationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.QueryExp;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

import test.jmx.HelloWorld;

public class JconTest {
	
	public static void main(String[] args) throws Exception{
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
//		MBeanServer ms = MBeanServerFactory.createMBeanServer();
		
		ZooMXBean zoo = new ZooImpl();
		ObjectName name = new ObjectName("Zoo:type=firstMXBean");
		ms.registerMBean(zoo, name);
		
		CompositeData cdate = (CompositeData)ms.getAttribute(name, "Tiger");
		String s = (String)cdate.get("name");
		
		
		CompositeType ct1 = new CompositeType("test.jmx.mxbean.Meat", " meat---", 
				new String[]{"desc"},
				new String[]{"-meat descript-"},
				new OpenType[]{SimpleType.STRING});
		
		CompositeData ct1V = new CompositeDataSupport(ct1,
				new String[]{"desc"},
		   		new Object[]{" delicious meat"});
		
		CompositeType ct2 = new CompositeType("test.jmx.mxbean.Tiger", " tiger---",
				new String[]{"name","meat"},
				new String[]{"-name-","-meat-"}, 
				new OpenType[]{SimpleType.STRING,ct1});
		
		CompositeData ct2V = new CompositeDataSupport(ct2,
				new String[]{"name","meat"},
		   		new Object[]{"hi,man",ct1V}); 
		
		
		ms.invoke(name, "addTiger", 
				new Object[]{ct2V},
				new String[]{CompositeData.class.getName()});
		
//		ms.setAttribute(name, new Attribute("Name", "bbcc"));
//		CompositeData cdate2 = (CompositeData)ms.invoke(name, "roar",null,null);
		
//		String s2 = (String)cdate.get("roar");
		CompositeData cdate2 = (CompositeData)ms.getAttribute(name, "Tiger");
		String s2 = (String) ((CompositeData)cdate2.get("meat")).get("desc");
		System.out.println(s);
		System.out.println(s2);
		System.out.println(ct2V);
		Thread.sleep(Long.MAX_VALUE);
	}
}
