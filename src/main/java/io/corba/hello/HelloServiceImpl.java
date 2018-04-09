package io.corba.hello;

import org.omg.CORBA.ORB;

public class HelloServiceImpl extends HelloServicePOA {

	private ORB orb;
	
	public void set(ORB orb_val){
		this.orb = orb_val;
	}
	@Override
	public String sayHello() {

		return "\nHello world!!\n";
	}

	@Override
	public void shutdown() {
		orb.shutdown(false);
	}

}
