package testxml;

import java.lang.reflect.Method;

public class ObjectFactory {
	
	XmlProvider xmlProvider = new XmlProvider("Test\\testxml\\bean.xml");
	
	public <T> T getInstance(Class<T> clazz){
		
		IocBean iocBean = xmlProvider.getBean(clazz);
		T t = null;
		try {
			t = clazz.newInstance();
			if(iocBean == null)
				return t;
			
			Class refClass = Class.forName(iocBean.getReferencePath());
			Object refObject = refClass.newInstance();
//			Class mainClass = Class.forName(iocBean.getPath());
//			Object o = mainClass.newInstance();
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				System.out.println(refClass.getSimpleName());
				if(method.getName().toLowerCase().equals("set"+refClass.getSimpleName().toLowerCase())){ 
					method.invoke(t, refObject);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
}
