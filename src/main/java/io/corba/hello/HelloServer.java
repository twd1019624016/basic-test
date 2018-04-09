package io.corba.hello;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class HelloServer {

	public static void main(String[] args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {

		if(args == null || args.length == 0){
			args = new String[]{"-ORBInitialPort","900","-ORBInitialHost","localhost"};
		}
		ORB orb = ORB.init(args, null);

		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();
		
		HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
		helloServiceImpl.set(orb);
		
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloServiceImpl);
		HelloService href = HelloServiceHelper.narrow(ref);
		
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		
		String name="HelloService";
		NameComponent path[]	= ncRef.to_name(name);
		ncRef.rebind(path, href);
		
		System.out.println("HelloServer ready and waiting ...");
		
		orb.run();
		
		
		
		
		
		
		
	}
}
