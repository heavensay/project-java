package test.jmx.mxbean.simple;

import java.util.ArrayList;
import java.util.List;

public class ZooImpl implements ZooMXBean {

	private String zooName = " China zoo";
	private static List<Tiger> list;
	static {
		//初始化一只Tiger
		Tiger tiger = new Tiger(" the first tiger");
		list = new ArrayList<Tiger>();
		list.add(tiger);
	}
	public void addTiger(Tiger tiger) {
		list.add(tiger);
	}

	public Tiger getTiger() {
		return list.get(0);
	}

	public int getTigerCount(){
		return list.size();
	}
	
	public String getZooName() {
		return zooName;
	}
	
	public String[] getAnimalNames(){
		return new String[]{"bird","tiger","mouse"};
	};
}
