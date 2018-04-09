package io.rmi.hello;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class SimpleClient {

	public static void showRemoteObjects(Context namingContext) throws Exception {

		NamingEnumeration<NameClassPair> e = namingContext.list("rmi:");
		while (e.hasMore()) {
			System.out.println(e.next().getName());
		}
	}

	public static void main(String[] args) throws Exception {
		String url = "rmi://localhost/";

		Context namingContext = new InitialContext();
		showRemoteObjects(namingContext);
		
		HelloService service1 = (HelloService) namingContext.lookup(url + "HelloService1");
																			
		HelloService service2 = (HelloService) namingContext.lookup(url + "HelloService2");

		Class<? extends HelloService> class1 = service1.getClass();
		System.out.println("service1 是"+class1.getName() + "的实例");
	
		Class<?>[] interfaces = class1.getInterfaces();
		
		for (Class<?> class2 : interfaces) {
			
			System.out.println("存根类实现了"+class2.getName());
		}
		
		System.out.println(service1.echo("hello"));
		
	
	
	
	
	
	
	
	
	
	
	
	}
}
