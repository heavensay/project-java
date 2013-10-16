package oracle;

import java.util.HashMap;

public class Generic<X> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Generic g = new Generic();
		Object a  = g.getGeneric(null);
		if (a instanceof Animal){
			((Animal)a).f1();
		}
	}

	public  X getGeneric(X x){
		Animal a = new Animal();
		return (X)a;
	}
	
}
