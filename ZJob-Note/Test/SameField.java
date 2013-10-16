
public class SameField extends A{

	public static void main(String[] args){
		SameField sf = new SameField();
		sf.setN("a");
		System.out.println(sf.n);
	}
	
	private int n;

}
class A{
	private String n;

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}
	
}