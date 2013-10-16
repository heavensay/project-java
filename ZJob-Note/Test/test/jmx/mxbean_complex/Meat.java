package test.jmx.mxbean_complex;

import java.beans.ConstructorProperties;


public class Meat {
	private String desc;
	
	@ConstructorProperties({"desc"})
	public Meat(String desc){
		this.desc = " i am a meat ";
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public Object setObject(Object o){
		return o;
	}
}
