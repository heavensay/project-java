package test.jmx.mxbean.simple;

import java.beans.ConstructorProperties;


public class Tiger {
	
	private String name;
	@ConstructorProperties({})
	public Tiger(){
		this.name = "the default constructor";
	}
	
	@ConstructorProperties({"name"})
	public Tiger(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String roar(){
		return "@￥%%……";
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String[] getFoodNames(){
		return new String[]{"rabbit","sheep","pig"};
	}
}
