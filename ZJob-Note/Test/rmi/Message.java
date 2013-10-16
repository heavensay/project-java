package rmi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.Remote;

public class Message implements Serializable {
	
	private void writeObject(ObjectOutputStream os){
		System.out.println(" Message i be writed--");
	}
	
	private void readObject(ObjectInputStream in){
		System.out.println(" Message i be read--");
	}
	
	public String getMessage(){
		return "message info detail";
	}
	
}
