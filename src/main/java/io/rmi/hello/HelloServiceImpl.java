package io.rmi.hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private static AtomicInteger count = new AtomicInteger(1);

	public HelloServiceImpl(String name) throws RemoteException {
		super();
		this.name = name;

	}

	@Override
	public String echo(String msg) throws RemoteException {
		System.out.println(name + ":调用echo方法");
		return "echo:" + msg + "from " + name;
	}

	@Override
	public Date getTime() throws RemoteException {

		return new Date();
	}

}
