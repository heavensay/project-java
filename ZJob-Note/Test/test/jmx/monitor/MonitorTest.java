package test.jmx.monitor;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.monitor.CounterMonitor;
import javax.management.monitor.Monitor;
import javax.management.monitor.MonitorNotification;

import org.junit.Test;

public class MonitorTest {
	
	@Test
	public void test1CountMonitor() throws Exception{
		MBeanServer ms = MBeanServerFactory.createMBeanServer();
		
		final Count count = new Count(0);
		final ObjectName nameCount = new ObjectName("Count:type=StandardMBean");
		ms.registerMBean(count, nameCount);
		
		//这个监听器类主要用于监听一个对象的属性(数值类型)
		CounterMonitor cMonitor = new CounterMonitor();
		//被监听的对象
		cMonitor.addObservedObject(nameCount);
		//被监听者的属性
		cMonitor.setObservedAttribute("Count");
		//阈值为3，超过的话，触发事件
		cMonitor.setInitThreshold(4);
		//间隔时间
		cMonitor.setGranularityPeriod(1000);
		//接受监控的事件
		cMonitor.setNotify(true);
		//监控期间没发现超过一次阈值，那么阈值偏移值往上加 threshold = threshold+offset;
		cMonitor.setOffset(4);
		//注册一个Lister，用来监听Monitor所产生的消息(事件)
		cMonitor.addNotificationListener(new NotificationListener() {
			@Override
			public void handleNotification(Notification notification, Object handback) {
				MonitorNotification n = (MonitorNotification)notification;
				CounterMonitor monitor = (CounterMonitor)n.getSource();
				if(MonitorNotification.THRESHOLD_VALUE_EXCEEDED.equals(n.getType())){
					System.out.println("Count类-"+monitor.getObservedAttribute()+"属性:"+n.getDerivedGauge()
							+">=了设定的阈值:"+n.getTrigger()+";下个阈值："+monitor.getThreshold(nameCount));
				}else{
					System.out.println(n.getType()+"--"+n);
				}
			}
		},null,null);
		
		//Monitor本身就是一个标准MBean，而且Monitor也必须被注册到MBeanServer中
		ObjectName nameMonitor = new ObjectName("CountMonitor:type=Monitor");
		ms.registerMBean(cMonitor, nameMonitor);
		//开始监控
		cMonitor.start();
		
		for(int i=0;i<5;i++){
			count.addNumber();
			Thread.sleep(2000);
		}
		Thread.sleep(5000);;
	}
	
}
