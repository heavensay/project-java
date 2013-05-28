package ioc;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

public class Zoo {

	private String name;
	@Autowired
	private Tiger tiger;

	private Timestamp opentime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tiger getTiger() {
		return tiger;
	}

	// public void setTiger(Tiger tiger) {
	// this.tiger = tiger;
	// }
	Object initM() {
		this.name = " the zoo name is inited ";
		return new Object();
	}

	public Timestamp getOpentime() {
		return opentime;
	}

	public void setOpentime(Timestamp opentime) {
		this.opentime = opentime;
	}

	public void setTiger(Tiger tiger) {
		this.tiger = tiger;
	}
}
