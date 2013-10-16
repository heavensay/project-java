package testxml;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlProvider {

	private String path;
	
	public XmlProvider(String path){
		this.path = path;
		parse();
	}
	
	private Map<String, IocBean> map = new HashMap<String, IocBean>();

	public void parse() {
		SAXReader reader = new SAXReader(true);
		try {

			Document document = reader.read(new File(path));
			Element root = document.getRootElement();
			Iterator<Element> rootIterator = root.elementIterator();

			Class clazz = IocBean.class;
			Method[] methods = clazz.getDeclaredMethods();

			while (rootIterator.hasNext()) {
				IocBean iocBean = new IocBean();
				Element element = rootIterator.next();
				// bean 设置属性
				Iterator<Attribute> attributeIterator = element.attributeIterator();
				while (attributeIterator.hasNext()) {
					Attribute attribute = attributeIterator.next();
					for (Method method : methods) {
						if (method.getName().toLowerCase().equals(
								"set" + attribute.getName().toLowerCase())) {
							method.invoke(iocBean, attribute.getValue());
						}
					}
				}
				// bean 设置子节点
				Iterator<Node> nodes = element.nodeIterator();
				while (nodes.hasNext()) {

					Node node = nodes.next();

					for (Method method : methods) {
						if (method.getName().toLowerCase().equals(
								"set" + node.getName().toLowerCase())) {
							method.invoke(iocBean, node.getText());
						}
					}
				}
				map.put(iocBean.getPath(), iocBean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IocBean getBean(Class clazz){
		IocBean iocBean = map.get(clazz.getPackage().getName()+"."+clazz.getSimpleName());
		return iocBean;
	}

}
