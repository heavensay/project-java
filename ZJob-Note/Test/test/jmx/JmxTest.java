package test.jmx;

import java.util.Set;

import javax.management.Attribute;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.modelmbean.RequiredModelMBean;

import org.junit.Test;

public class JmxTest {
	
	/**
	 * 测试标准MBean
	 * 需要被管理的方法、属性等在接口中定义好，创建一个类，继承此接口，然后实现时候方法， 
	 * 这样，但注册到MBeanServer的时候，会自动管理其，接口中的各个属性、方法。
	 * @throws Exception
	 */
	@Test
	public void test1StandardMBean() throws Exception{
//		MBeanServer ms = MBeanServerFactory.createMBeanServer("JMX2Test");
		MBeanServer ms = MBeanServerFactory.createMBeanServer();
		ObjectName name = new ObjectName("Hello:type=myfirstMbean");
		
//		ms.createMBean("HelloWorld", objectName);
		HelloWorld hello = new HelloWorld(" yao yao , qie ke nao ");
		
		//MBean需要实现NotificationBroadcaster接口，支持各种事件的发送和处理
		hello.addNotificationListener(new NotificationListener() {
			@Override
			public void handleNotification(Notification notification, Object handback) {
				System.out.println(" access listen : "+notification);
			}
		},null,null);
		
		ms.registerMBean(hello,name );
		
		String s1 = (String)ms.getAttribute(name, "Hello");
		System.out.println(" the init value : "+s1);
		
		ms.setAttribute(name, new Attribute("Hello"," hi ,hi ,man "));
		String s2 = (String)ms.getAttribute(name, "Hello");
		System.out.println(" the init value : "+s2);
		
		ms.invoke(name, "message", new Object[]{" i as message "}, new String[]{"java.lang.String"});

		ObjectName name2 = new ObjectName("*:*");
		Set<ObjectInstance> set = ms.queryMBeans(name2, null);
	}
	
	/**
	 * 动态Mbean，需要实现DynamicMBean接口，并且任何需要，管理的方法、属性，都需要在接口的方法中，
	 * 自己来实现，Mbeaninfo也需要自己设置，这样编程的工作量大，但是有很大的可控性。
	 * @throws Exception
	 */
	@Test
	public void test2DynamicMBean() throws Exception{
		HelloWorldDynamic dynamic = new HelloWorldDynamic();
		
		MBeanServer ms = MBeanServerFactory.createMBeanServer();
		//创建一个ObjectName
		ObjectName name = new ObjectName("DynamicHello:type=dinamicMbean");
		
		//注册动态MBean到MBeanServer服务上去
		ms.registerMBean(dynamic, name);
		
		//得到属性值
		Object o = ms.getAttribute(name, "getInstance");
		String hello = (String)ms.getAttribute(name, "gh");
		MBeanOperationInfo operation = dynamic.getMBeanInfo().getOperations()[0];
		System.out.println(" attribute value of getInstance:"+o+"; attribute value of gh:"+hello);
		
		//执行一个方法(操作)
		ms.invoke(name, operation.getName(), null, null);
	}
	
	@Test
	public void test3RequiredModelMBean() throws Exception{
		HelloWorldModelMBean hello = new HelloWorldModelMBean();
		
		MBeanServer ms = MBeanServerFactory.createMBeanServer();
		RequiredModelMBean modelMbean = hello.createModelBean();
		ObjectName name = new ObjectName("RequiredMBeanHello:type=ModelMbean");
		//监听属性变化事件
		modelMbean.addAttributeChangeNotificationListener(new NotificationListener() {
			@Override
			public void handleNotification(Notification notification, Object handback) {
				System.out.println(" --Attribute已经改变-- ");
			}
		}, null,null);
		ms.registerMBean(modelMbean, name);
		
		ms.invoke(name, "setHello", new Object[]{" aaa "},new String[]{ "java.lang.String"});
		String s = (String)ms.getAttribute(name, "hello");
		//出发Attribute改变事件
		ms.setAttribute(name, new Attribute("hello", "bbb"));
		String s2 = (String)ms.getAttribute(name, "hello");
		System.out.println(s);
		System.out.println(s2);
	}
}
