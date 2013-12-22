package aop;

import org.springframework.beans.factory.annotation.Autowired;

public class Zoo implements IZoo{

	Object initM() {
		System.out.println(" the initM be invoked ");
		return new Object();
	}

	public void open(){
		System.out.println(" the zoo is open ");
	}
	
	public void close(String str){
		System.out.println(" the zoo is close ... "+str);
	}
	
}
