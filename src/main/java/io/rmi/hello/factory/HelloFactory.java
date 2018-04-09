package io.rmi.hello.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import io.rmi.hello.HelloService;

public interface HelloFactory extends Remote{

	public HelloService getHelloService(String serviceName) throws RemoteException;
}
