package io.rmi.hello;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleServer {
	
	public static void main(String[] args) throws RemoteException, NamingException {
		
		HelloService service1 = new HelloServiceImpl("service1");
		HelloService service2 = new HelloServiceImpl("service2");
		
		
		Context namingContext = new InitialContext();
		namingContext.rebind("rmi:HelloService1", service1);
		namingContext.rebind("rmi:HelloService2", service2);
		
		System.out.println("服务器注册了两个helloservice对象");
	}
}
