package io.rmi.hello.factory;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import io.rmi.hello.HelloService;

public class SimpleClient {

	public static void main(String[] args) throws NamingException, RemoteException {
		String url="rmi://localhost/";
		
		Context namingContext = new InitialContext();
		NamingEnumeration<NameClassPair> list = namingContext.list(url);
		while(list.hasMore()) {
			System.out.println(list.next().getName());
		}
		
		HelloFactory helloFactory = (HelloFactory) namingContext.lookup(url+"HelloFactory");
		for(int i=0;i<10000;i++) {
		HelloService helloService = helloFactory.getHelloService("service12"+i);
		helloService.echo("hello");
		}
		
	}
}
