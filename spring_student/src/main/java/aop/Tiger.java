package aop;

import org.springframework.stereotype.Component;

@Component
public class Tiger {
	
	public void getName(){
		System.out.println(" i am a tiger");
	}
	
	public void before(){
		System.out.println(" before tiger");
	}
	
}
