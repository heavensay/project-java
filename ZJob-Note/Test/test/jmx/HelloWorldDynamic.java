package test.jmx;

import java.lang.reflect.Method;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

public class HelloWorldDynamic implements DynamicMBean {
	public String hello;
	

	public HelloWorldDynamic() {
		this.hello = "Hello World! I am a Dynamic MBean";
	}

	public HelloWorldDynamic(String hello) {
		this.hello = hello;
	}

	public String getHello() {
		return hello;
	}

	public Object getInstance() {
		return new Object();
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException,
			ReflectionException {
		//设置getAttribute的执行逻辑
		if("getInstance".equals(attribute)){
			return getInstance();
		}
		
		return null;
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	MBeanInfo info = null;
	@Override
	public MBeanInfo getMBeanInfo()  {
		try {
			Class cls = this.getClass();
			// 用反射获得 "getHello" 属性的读方法
			//DynamicMBean中，
			Method readMethod = cls.getMethod("getHello", new Class[0]);
			MBeanAttributeInfo attribute = new MBeanAttributeInfo("gh",
					" the first attribute ", readMethod, null);
			//执行java类的method需要的一些元数据，由MBeanOperationInfo提供
			MBeanOperationInfo operation = new MBeanOperationInfo(
					" the first operation ", cls.getMethod("getInstance", null));
			info = new MBeanInfo(cls.getName(), " this is a dynamic MBean ",
					new MBeanAttributeInfo[] { attribute }, null,
					new MBeanOperationInfo[] { operation }, null);
		} catch (Exception e) {
			System.out.println(e);
		}
		return info;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		System.out.println(" the HelloWorldDynamic's method invoke  ");
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		return null;
	}
}
