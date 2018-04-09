package io.rmi.hello.factory;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleServer {

	public static void main(String[] args) throws RemoteException, NamingException {
		
		HelloFactory helloFactory = new HelloServiceFactoryImpl();
		
		Context namingContext = new InitialContext();
		namingContext.rebind("rmi:HelloFactory",helloFactory);
		
		System.out.println("服务器注册了一个工厂对象");
	}
}
