package TestRefect;
import java.util.Map;


public class TestObject {
	
	TestObject(){
		super();
	}
	public TestObject(int s){
		System.out.println(s);
	}
	private TestObject(int i,Integer i2) throws Exception{
		System.out.println("i am a have avgurments Construtctor");
	}
	
	public String getString(){
		return "--getString()";
	}
	
	private String getPrivateString(){
		return "--getPrivateString";
	}
	
	public  String getPublicString(){
		return "--getPublicString";
	}
	
	final String getFinalString(){
		return "--getFinalString";
	}
	
	private void empty(String s,Map<String,Integer> map){
		
	}

	private void emptyException(String s,Map<String,Integer> map) throws ClassNotFoundException,ClassCastException{
		
	}
	
	public final void bagOrOrigin(int a,Integer b){
		System.out.println(a);
		System.out.println(b);
	}
	
}
