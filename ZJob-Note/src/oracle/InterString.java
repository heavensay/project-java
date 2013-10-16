package oracle;

public class InterString {
	
	static String str;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InterString is = new InterString();
//		String s = args[0];
		String s = "a";
		String s2 = "a";
		is.f(s);
		System.out.println(s==str);
	}

	void f(String s){
		str = s;
		String s3 = "a";
		System.out.println(str==s3);
	}
	
}
