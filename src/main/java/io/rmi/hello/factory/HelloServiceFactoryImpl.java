
package io.rmi.hello.factory;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import io.rmi.hello.HelloService;
import io.rmi.hello.HelloServiceImpl;

public class HelloServiceFactoryImpl implements HelloFactory {

	protected HashMap<String, HelloService> services;
	
	public HelloServiceFactoryImpl() throws RemoteException {
		this.services = new HashMap<>();
	
		UnicastRemoteObject.exportObject(this,22222);
	}

	@Override
	public HelloService getHelloService(String serviceName) throws RemoteException {
		System.err.println("调用服务器getService");
		HelloService helloService = services.get(serviceName);
		if(null != helloService) {
			return helloService;
		}
		helloService = new HelloServiceImpl(serviceName);
		services.put(serviceName, helloService);
		return helloService;
	}
					
}
