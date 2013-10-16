package testCollection_hashcode;

import junit.framework.TestCase;

public class OperatorTest extends TestCase {
	public void test1(){
		System.out.println( Integer.toBinaryString(8)+":"+(8>>>2) );
		System.out.println( Integer.toBinaryString(885656)+":"+(885656>>2) );
		System.out.println( Integer.toBinaryString(-8)+":"+(-8>>2) );
	}
	
	public void test2specialOperator(){
		// 8:  1000
		// ^
		// 6:  0110
		//14 = 1110
		System.out.println( 8^6 ); //out:14
		int b=8;
		int c=6;
		System.out.println( b^=c^=5 ); //out:11
		int a=1;
		// 1:  0001
		// ^
		// 6:  0110
		// 7 = 0111
		System.out.println( (a^=6) ); //out:7
	}
}
