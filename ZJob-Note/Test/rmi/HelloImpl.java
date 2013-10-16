package rmi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello{

	public HelloImpl() throws RemoteException{
		
	}
	@Override
	public Message getMessage() throws RemoteException {
		return new Message();
	}
	
	private void readObject(ObjectInputStream in){
		System.out.println(" HelloImpl i be read");
	}

	private void writeObject(ObjectOutputStream os){
		System.out.println(" HelloImpl Message i be writed");
	}
}
