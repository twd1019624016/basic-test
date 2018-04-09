package io.corba.hello;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class HelloClient {

	public static void main(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
		
		if(args == null || args.length == 0){
			args = new String[]{"-ORBInitialPort","900","-ORBInitialHost","localhost"};
		}
		ORB orb = ORB.init(args, null);
		
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		
		String name="HelloService";
		HelloService helloService = HelloServiceHelper.narrow(ncRef.resolve_str(name));
	
	
		System.out.println(helloService.sayHello());
		
	
	}
}
