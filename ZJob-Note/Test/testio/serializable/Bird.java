package testio.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bird implements Serializable {
	
	private String s;
	transient int x;
	
	/**
	 * 这2个方法必须具有准确的特征签名，
	 * @param stream
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException{
		stream.defaultWriteObject();
		stream.writeInt(x);
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
		stream.defaultReadObject();
		x = stream.readInt();
	}
	
	public Bird(){
		System.out.println("public Bird()");
	}
	public Bird(int x,String s){
		this.x = x;
		this.s = s;
		System.out.println("public Bird()");
	}
	
}
