package io.rmi.hello.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote{

	public void callback(String msg) throws RemoteException;
}
