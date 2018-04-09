package io.rmi.hello.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCallbackImpl implements ClientCallback{

	
	
	public ClientCallbackImpl() throws RemoteException {
		super();
		UnicastRemoteObject.exportObject(this,0);
	}

	@Override
	public void callback(String msg) {
		System.out.println("service:"+msg);
		
	}

}
