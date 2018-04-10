package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiClient {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        Hello hello = (Hello) Naming.lookup("hello");
        Message message = hello.getMessage();
        System.out.println(message);
        System.out.println(message.getMessage());
    }

}
