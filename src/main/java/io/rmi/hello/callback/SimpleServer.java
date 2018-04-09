package io.rmi.hello.callback;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleServer {
	
	public static void main(String[] args) throws RemoteException, NamingException {
		
		HelloServiceCallbackImpl serviceCallback = new HelloServiceCallbackImpl();
		Context namingContext = new InitialContext();
		namingContext.rebind("rmi:HelloServiceCallback", serviceCallback);
		
		System.out.println("服务器注册了serviceCallback对象");
		
		new Thread(serviceCallback).start();
	}
}
