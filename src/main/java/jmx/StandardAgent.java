package jmx;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class StandardAgent {

	private MBeanServer mBeanServer = null;

	public StandardAgent() {
		
		this.mBeanServer =MBeanServerFactory.createMBeanServer();
	}

	public MBeanServer getmBeanServer() {
		return mBeanServer;
	}


	public ObjectName createObjectName(String name) {
		
		ObjectName objectName = null;
		
		try {
			objectName = new ObjectName(name);
		} catch (MalformedObjectNameException e) {
			
			e.printStackTrace();
		}
		
		return objectName;
	}
	
	
	private void createStandardBean(ObjectName objectName,String managedResourceClassName) {
		
		try {
			mBeanServer.createMBean(managedResourceClassName, objectName);
		} catch (InstanceAlreadyExistsException | NotCompliantMBeanException | MBeanException | ReflectionException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		StandardAgent agent = new StandardAgent();
		MBeanServer mBeanServer = agent.getmBeanServer();
		
		String defaultDomain = mBeanServer.getDefaultDomain();
		System.out.println(defaultDomain);
		
		
		String managerResourceClassName = Car.class.getName();
		System.out.println(managerResourceClassName);
		
		ObjectName createObjectName = agent.createObjectName(defaultDomain+":type="+managerResourceClassName);
		
		agent.createStandardBean(createObjectName, managerResourceClassName);
		
		
		Attribute attribute = new Attribute("Color", "blue");
		try {
			mBeanServer.setAttribute(createObjectName, attribute);
			System.out.println(mBeanServer.getAttribute(createObjectName, "Color"));
			
			mBeanServer.invoke(createObjectName, "drive", null, null);
		} catch (InstanceNotFoundException | InvalidAttributeValueException | AttributeNotFoundException
				| ReflectionException | MBeanException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
