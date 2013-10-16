package TestRefect;

public class PrivaeClass {
	
	public void f1(){
		C1 c = new C1();
	}
	
	private class C1{
		private int a = 5;
	}
	
	public static void main(String[] args){
		PrivaeClass p = new PrivaeClass();
		C1 c1 = p.new C1();
		System.out.println(c1.a);
	}
	
}
class C2{
	private int a = 5;
}