package testCollection_hashcode;

import java.util.Hashtable;

import junit.framework.TestCase;

public class HashcodeTest extends TestCase {
	Point p1 = new Point(1,2);
	Point p2 = new Point(1,2);
	ExtendPoint ep = new ExtendPoint(1,2,3);

	public void test1equals(){
		System.out.println(ep.equals(p1));
		System.out.println("hashcode1:"+p1.hashCode()+" hashcode2:"+p2.hashCode());
	}
	/**
	 * hashCode约定
	 * 1. 在同一应用中，一个对象的hashCode函数在equals函数没有更改的情况下，无论调用多少次，它都必须返回同一个整数。
		2. 两个对象如果调用equals函数是相等的话，那么调用hashCode函数一定会返回相同的整数。
		3. 两个对象如果调用equals函数是不相等的话，那么调用hashCode函数不要求一定返回不同的整数。 
		
		note:当我们改写equals函数的时候，通常都需要改写hashCode函数
	 */
	public void test2hashcode(){
		Hashtable<Point,String> h = new Hashtable<Point,String>();
		h.put(p1, "p1-value");
		System.out.println("p1.equals(p2):"+p1.equals(p2));
		//Point 的hashcode 重写
		System.out.println(p1==p2);
		System.out.println(h.get(p2));
	}
	
	
	
	class Point {
		
		private int x;
		
		private int y;

		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if(! (obj instanceof Point))
				return false;
			Point other = (Point)obj;
			return x == other.x && y == other.y;
		}
	}

	class ExtendPoint extends Point {
		private int z ;
		ExtendPoint(int x,int y,int z){
			super(x,y);
			this.z = z;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof ExtendPoint))
				return false;
			ExtendPoint ep = (ExtendPoint)obj;
			return super.equals(obj) && z == ep.z;
		}
	}
}
