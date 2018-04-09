package io.rmi.hello.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class HelloServiceCallbackImpl implements HelloServiceCallback,Runnable {

	private List<ClientCallback> list = new ArrayList<>();

	public HelloServiceCallbackImpl() throws RemoteException {
		super();
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void registerCallback(ClientCallback callback) throws RemoteException {
        System.out.println(callback.getClass());
		list.add(callback);
	}

	public List<ClientCallback> getList() {
		return list;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			for (ClientCallback clientCallback : list) {
				try {
					clientCallback.callback("hello");
				} catch (RemoteException e) {

					e.printStackTrace();
				}
			}
		}
	}

}
