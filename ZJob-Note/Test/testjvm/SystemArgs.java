package testjvm;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class SystemArgs {
	public static void main(String[] args){
		Properties  props = System.getProperties();
		Enumeration enums = props.elements();
		while(enums.hasMoreElements()){
			Object arg = enums.nextElement();
			System.out.println(arg);
		}
		Set sets =  props.entrySet();
		Iterator iter = sets.iterator();
		while(iter.hasNext()){
			Object key  = iter.next();
			System.out.println(key);
		}
		
	}
}
