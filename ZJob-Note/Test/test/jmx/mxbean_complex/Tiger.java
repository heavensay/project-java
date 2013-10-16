package test.jmx.mxbean_complex;

import java.beans.ConstructorProperties;


public class Tiger {
	
	private String name;
	private Meat meat ; 
	
	@ConstructorProperties({"meat"})
	public Tiger(Meat meat){
		this.name = "aa";
		this.meat = meat;
	}
	
	public String getName(){
//		return " i'm a tiger ";
		return name;
	}
	
	public String roar(){
		return "@￥%%……";
	}
	
	public Meat getMeat(){
		return meat;
	}
	public void setName(String name){
		this.name=name;
	}
}
