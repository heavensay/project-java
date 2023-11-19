package test.jmx.openMBean;

import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

import org.junit.Test;

import com.sun.jmx.mbeanserver.DefaultMXBeanMappingFactory;
import com.sun.jmx.mbeanserver.MXBeanMapping;
import com.sun.jmx.mbeanserver.MXBeanMappingFactory;


public class TestOpentype {

    @Test
    public void test1() throws Exception {
        //这个注释代码构造的一个CompositeData，代表了Tiger
//		CompositeType ct = new CompositeType("test.jmx.mxbean.Tiger",
//				" tiger---", new String[] { "name","foodNames" },
//				new String[] { "-name-","-foodNames-" },
//				new OpenType[] { SimpleType.STRING,new ArrayType(SimpleType.STRING, false) });
//
//		CompositeData ctV = new CompositeDataSupport(ct,
//				new String[] { "name","foodNames" }, new Object[] { "tiger2" ,new String[]{"food1","food2","food3"}});
//		return ctV;


        DefaultMXBeanMappingFactory mapType = new DefaultMXBeanMappingFactory();
        MXBeanMapping m = mapType.mappingForType(Tiger.class, MXBeanMappingFactory.DEFAULT);
        CompositeData openTiger = (CompositeData) m.toOpenValue(new Tiger("clinet_tiger"));

        MXBeanMapping m2 = mapType.mappingForType(String.class, MXBeanMappingFactory.DEFAULT);
        MXBeanMapping m3 = mapType.mappingForType(String[].class, MXBeanMappingFactory.DEFAULT);
        MXBeanMapping m4 = mapType.mappingForType(CompositeData[].class, MXBeanMappingFactory.DEFAULT);
        MXBeanMapping m5 = mapType.mappingForType(CompositeData.class, MXBeanMappingFactory.DEFAULT);
        MXBeanMapping m6 = mapType.mappingForType(SimpleType.STRING.getClass(), MXBeanMappingFactory.DEFAULT);

        Object type21 = m2.getOpenType();
        Object type22 = m2.getOpenClass();
        Object type31 = m3.getOpenType();
        Object type32 = m3.getOpenClass();
        Object type41 = m4.getOpenType();
        Object type42 = m4.getOpenClass();
        Object type51 = m5.getOpenType();
        Object type52 = m5.getOpenClass();
        Object type61 = m6.getOpenType();
        Object type62 = m6.getOpenClass();


        Object o1 = m2.toOpenValue(new String("b"));
//		CompositeData data = new CompositeDataSupport(
//				new CompositeType("abcde",
//						"--", new String[]{"aa"}, new String[]{"ddd"}, new OpenType[]{SimpleType.STRING}), 
//				new String[]{"aa"},new String[]{"asdsadfasdfd"});
//		Object o6 = m6.toOpenValue(data);

        Object o2 = m3.toOpenValue(new String[]{"c", "d"});
        Object o3 = m5.toOpenValue(openTiger);
        Object o4 = m.fromOpenValue(openTiger);
        Object o5 = m4.toOpenValue(new CompositeData[]{openTiger, openTiger});


        String name1 = new String[]{"1", "2"}.getClass().getName();
        String name2 = new String[][]{{"a", "b"}, {"c"}}.getClass().getName();
        System.out.println(name1);
        System.out.println(name2);
    }
}
