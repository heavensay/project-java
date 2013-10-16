package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
//		System.setProperty( "java.rmi.server.hostname", "127.0.0.1");
		LocateRegistry.createRegistry(1099);
		Hello hello = new HelloImpl();
		Naming.bind("hello", hello);
		
	}

}
