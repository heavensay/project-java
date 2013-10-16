package test.jmx.openMBean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.Descriptor;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenMBeanAttributeInfo;
import javax.management.openmbean.OpenMBeanAttributeInfoSupport;
import javax.management.openmbean.OpenMBeanInfo;
import javax.management.openmbean.OpenMBeanInfoSupport;
import javax.management.openmbean.OpenMBeanOperationInfo;
import javax.management.openmbean.OpenMBeanOperationInfoSupport;
import javax.management.openmbean.OpenMBeanParameterInfo;
import javax.management.openmbean.OpenMBeanParameterInfoSupport;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

import com.sun.jmx.mbeanserver.DefaultMXBeanMappingFactory;
import com.sun.jmx.mbeanserver.MXBeanMapping;
import com.sun.jmx.mbeanserver.MXBeanMappingFactory;


public class ZooOpenMBean implements DynamicMBean {
	
	private ZooImpl zoo ;
	private OpenMBeanInfoSupport mbeanInfo;
	
	ZooOpenMBean(ZooImpl zoo) throws Exception{
		this.zoo=zoo;
		buildMBeanInfo();
	}

	/**
	 * 构造OpenMBeanInfo
	 * @return
	 */
	private OpenMBeanInfo buildMBeanInfo() throws Exception{
		Descriptor desc = new DescriptorSupport(
				new String[]{"openType","originalType"},
				new Object[]{SimpleType.STRING,"java.lang.String"});
		OpenMBeanAttributeInfo attribute1 = new OpenMBeanAttributeInfoSupport(
				"zooName", " the zoo's name ", SimpleType.STRING, 
				true,false,false/*,desc*/);
		
		CompositeType ct = new CompositeType("test.jmx.mxbean.Tiger",
				" tiger---", new String[] { "name","foodNames" },
				new String[] { "-name-","-foodNames-" },
				new OpenType[] { SimpleType.STRING,new ArrayType(SimpleType.STRING, false) });

		OpenMBeanAttributeInfo attribute2 = new OpenMBeanAttributeInfoSupport(
				"tiger", " tiger-- ", ct, 
				true,false,false);
		
		// operation addTShirt
		OpenMBeanParameterInfo[] params_add = new OpenMBeanParameterInfoSupport[1];
//		params_add[0] = new OpenMBeanParameterInfoSupport("tShirt", 
//								  "a TShirt", 
//								  tShirtType);
		OpenMBeanOperationInfoSupport operation = new OpenMBeanOperationInfoSupport
		    ("zooName",
		     " get the zoo's name ",
		     null, 
		     SimpleType.STRING, 
		     MBeanOperationInfo.INFO);
		
//		OpenMBeanOperationInfo operator = new OpenMBeanAttributeInfoSupport(name, description, openType, isReadable, isWritable, isIs);
		mbeanInfo = new OpenMBeanInfoSupport(zoo.getClass().getName(), 
				null, 
				new OpenMBeanAttributeInfo[]{attribute1,attribute2}, 
				null, 
				null/*new OpenMBeanOperationInfo[]{operation}*/, 
				null);
		
		return mbeanInfo;
	}
	
	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException,
			ReflectionException {
		if(attribute.equals("zooName")){
			//基本类型
			return zoo.getZooName();
		}if(attribute.equals("animalNames")){
			//返回个数组
			return zoo.getAnimalNames();
		}if(attribute.equals("tigerCount")){
			//int
			return zoo.getTigerCount();
		}else if (attribute.equals("tiger")){
			//返回一个CompositeType->CompositeData代表的Tiger对象
			//Client没有Tiger类的字节码，返回CompositeData这个Open Value
			try {
				Tiger tiger = zoo.getTiger();
				CompositeData openTiger = builtCompositeData(tiger);
				return openTiger;
				
			} catch (OpenDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @return CompositeData 是一个Open value， 代表了Tiger实例
	 * @throws Exception
	 */
	private static CompositeData builtCompositeData(Tiger tiger) throws OpenDataException{
		
		/**
		//这个注释代码构造的一个CompositeData，代表了Tiger				
		CompositeType ct = new CompositeType("test.jmx.mxbean.Tiger",
				" tiger---", new String[] { "name","foodNames" },
				new String[] { "-name-","-foodNames-" },
				new OpenType[] { SimpleType.STRING,new ArrayType(SimpleType.STRING, false) });

		CompositeData ctV = new CompositeDataSupport(ct,
				new String[] { "name","foodNames" }, new Object[] { "tiger2" ,new String[]{"food1","food2","food3"}});
		return ctV;
		**/
	
		//DefaultMXBeanMappingFactory，用于Open type<--->Java type的转换
		//DefaultMXBeanMappingFactory与MXBeanMapping存在sun包中，主要用于转换 MXBean的，
		//但是对于MXBean的操作的参数、返回值、属性、构造器参数类型等也都是Open Type/value;
		//oracle jdk7对MXBean默认有实现了，现在拿其中相关工具类来测试用
		DefaultMXBeanMappingFactory mapType = new DefaultMXBeanMappingFactory();
		//构造Tiger和Open type的映射
		MXBeanMapping m = mapType.mappingForType(Tiger.class, MXBeanMappingFactory.DEFAULT);
		//把一个Tiger实例转化到open type->CompisteData
		CompositeData compositeData = (CompositeData)m.toOpenValue(tiger);
		//compositeData跟上面注释代码 ctV结构是一样的，都用Open type->CompositeData代表了一个Tiger
		return compositeData;
	}
	
	@Override
	public AttributeList getAttributes(String[] attributes) {
		Attribute attr = new Attribute("a","b");
		Tiger tiger = new Tiger("tiger3");
		CompositeData compositeData = null;
		try {
			compositeData = builtCompositeData(tiger);
		} catch (OpenDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Attribute attr2 = new Attribute("a",compositeData);
		AttributeList list = new AttributeList();
		list.add(attr);
		list.add(attr2);
		return list;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		return mbeanInfo;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		if(signature!=null && CompositeData.class.getName().equals(signature[0])){
			try{
			CompositeData data = (CompositeData)params[0];
			//
			DefaultMXBeanMappingFactory mapType = new DefaultMXBeanMappingFactory();
			//构造Tiger和Open type的映射
			MXBeanMapping m = mapType.mappingForType(Tiger.class, MXBeanMappingFactory.DEFAULT);
			//把一个CompisteData转化到tiger
			Tiger tiger = (Tiger)m.fromOpenValue(data);
			//新增一只老虎
			zoo.addTiger(tiger);
			return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
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
