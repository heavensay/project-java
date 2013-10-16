package testjvm;

public class Room {

	public static void main(String[] args) {
		Door door = new Door();
		Object o = new Object();
		int i = 5;
		door.f(i,o);
		System.out.println("aa");
		System.out.println(door);
	}
	
	public void f(){
		int a = 5;
		int b = 6;
		int c = a+b;
	}

}
