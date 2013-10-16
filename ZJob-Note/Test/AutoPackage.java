import java.util.logging.Level;


public class AutoPackage {

	public static void main(String[] args) {
		Integer int1 = 30; 
		Integer int2 = 30; 
		Integer int3 = 0; 
		System.out.println(int1 == int2); 
		System.out.println(int1 == int2 + int3); 

		Integer int4 = new Integer(30); 
		Integer int5 = new Integer(30); 
		Integer int6 = new Integer(0); 
		System.out.println(int4 == int5); 
		System.out.println(int4 == int5 + int6);
		Level level = new Level("",1){};
	}

}
