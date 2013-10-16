package testxml;

public class Person {
	private String idcard;
	
	private String name;
	
	private String password;

	private Bird bird;
	
	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
