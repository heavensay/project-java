
public class StaticConstructor {
	static{
		System.out.println("-----static----1");
		int i = 5;
	}
	
	public static StaticConstructor s = new StaticConstructor();

	

	StaticConstructor(){
		System.out.println("------constructor------");
	}
	
	static{
		System.out.println("-----static---2-");
		int i = 5;
	}

	public static StaticConstructor getS() {
		return s;
	}
}
