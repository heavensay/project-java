package test.jmx;

import java.lang.reflect.Constructor;

import javax.management.Descriptor;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.RuntimeOperationsException;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanConstructorInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.RequiredModelMBean;

public class HelloWorldModelMBean extends RequiredModelMBean {

    public HelloWorldModelMBean() throws Exception {
    }

    public static RequiredModelMBean createModelBean()
            throws RuntimeOperationsException, MBeanException,
            InstanceNotFoundException, InvalidTargetObjectTypeException {
        // 模型MBean信息
        ModelMBeanInfo info = buildModelMBeanInfo();
        // 模型MBean
        RequiredModelMBean modelMBean = new RequiredModelMBean(info);
        //目前只支持ObjectReference，将来可能会支持ObjectReference", "Handle", "IOR", "EJBHandle",
        //         or "RMIReference，
        //RMIReference从名字上可以看出，如果支持的话，那么以后就可以支持远程方法等的调用
        modelMBean.setManagedResource(new HelloWorld(), "ObjectReference");
        return modelMBean;
    }

    protected static ModelMBeanInfo buildModelMBeanInfo() throws RuntimeOperationsException, MBeanException {
        // --  
        // attributes  
        // ------------------------------------------------------------------  
        ModelMBeanAttributeInfo[] attributes = new ModelMBeanAttributeInfo[1];

        // 设置属性
        Descriptor nameDesc = new DescriptorSupport();
        nameDesc.setField("name", "hello");
        nameDesc.setField("value", "----dfdf---");
        nameDesc.setField("displayName", "myname");
        nameDesc.setField("setMethod", "setHello");
        nameDesc.setField("getMethod", "getHello");
        nameDesc.setField("descriptorType", "attribute");
        attributes[0] = new ModelMBeanAttributeInfo("hello", "java.lang.String",
                "name say hello to", true, true, false, nameDesc);

        // --  
        // operations  
        // -------------------------------------------------------------------  
        ModelMBeanOperationInfo[] operations = new ModelMBeanOperationInfo[2];
        String className = HelloWorld.class.getName();

        // getName method  
        Descriptor getDesc = new DescriptorSupport(new String[]{
                "name=getHello", "descriptorType=operation",
                "class=" + className, "role=operation"});
        operations[0] = new ModelMBeanOperationInfo("getHello", "get hello ... ",
                null, null, MBeanOperationInfo.ACTION, getDesc);

        Descriptor setDesc = new DescriptorSupport(new String[]{
                "name=setHello", "descriptorType=operation",
                "class=" + className, "role=operation"});
        operations[1] = new ModelMBeanOperationInfo("setHello", "set hello ... ",
                new MBeanParameterInfo[]{new MBeanParameterInfo("a", "java.lang.String", " a method's arg ")},
                null, MBeanOperationInfo.ACTION, setDesc);

        // constructors  
        ModelMBeanConstructorInfo[] constructors = new ModelMBeanConstructorInfo[1];
        Constructor<?>[] ctors = HelloWorld.class.getConstructors();


        constructors[0] = new ModelMBeanConstructorInfo("default constructor",
                ctors[0], null);

        // ModelMBeanInfo  
        ModelMBeanInfo mmbeanInfo = new ModelMBeanInfoSupport(className,
                "Simple implementation of model bean.", attributes, null,
                operations/*null*/, null, null);

        //设置一个Descriptor策略，这样RequiredModelMBean改变 Attribute值得时候会记录日志
        //当然RequiredModelMBean还需要addAttributeChangeNotificationListener，注册一个监听器
        Descriptor globalDescriptor = new DescriptorSupport(new String[]{
                "name=HelloWorldModelMBean", "displayName=globaldescriptor",
                "descriptorType=mbean", "log=T", "logfile=hello.log"
        });
        mmbeanInfo.setMBeanDescriptor(globalDescriptor);

        return mmbeanInfo;
    }

}
