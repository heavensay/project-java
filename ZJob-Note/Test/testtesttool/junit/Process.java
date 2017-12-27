package testtesttool.junit;

public class Process {

	public void throwException(){
		throw new NullPointerException(" Process NullPointerException ");
	}
	
	public void throwNestedException(){
		try {
			throwException();	
		} catch (Exception e) {
			throw new IllegalArgumentException(" Process nested Exception  ");
		}
	}
	
}
