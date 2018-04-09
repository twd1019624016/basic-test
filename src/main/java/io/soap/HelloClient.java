package io.soap;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class HelloClient {

	public static void main(String[] args) throws ServiceException, RemoteException {
		
		String userName = "Guest";
		String endpoint="http://127.0.0.1:8080/axis/services/helloservice";
		//http://127.0.0.1:8080/axis/services/un:helloservice?method=sayHello&parameter=jjjj
		Service service = new Service();
		Call call = (Call)service.createCall();
		
		call.setTargetEndpointAddress(endpoint);
		call.setOperationName(new QName("un:helloservice","sayHello"));
		
		String result = (String)call.invoke(new Object[]{userName});
		
		System.out.println(result);
	}
}
