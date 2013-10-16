package TestRefect;
import java.util.Map;


public class TestPublicObject {
	
	private String aab001 = "init";
	
	TestPublicObject(){
		super();
	}
	private void getPublicStr(String s){
		System.out.print(s);
	}
	public String getAab001() {
		return aab001;
	}
	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}
	
}
