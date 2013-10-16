package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote{
	
	public Message getMessage() throws RemoteException;
	
}
