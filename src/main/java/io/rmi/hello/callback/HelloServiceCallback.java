package io.rmi.hello.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface HelloServiceCallback extends Remote{

	public void registerCallback(ClientCallback callback) throws RemoteException;
}
