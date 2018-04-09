package jmx;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.Descriptor;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.modelmbean.ModelMBean;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.RequiredModelMBean;

public class ModelAgent {

	private String MANAGED_CLASS_NAME = Car.class.getSimpleName();

	private MBeanServer mBeanServer;

	public ModelAgent() {
		mBeanServer = MBeanServerFactory.createMBeanServer();
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

	private ModelMBean createMBean(ObjectName objectName, String mbeanName) {

		ModelMBeanInfo mbeanInfo = createModelMBeanInfo(objectName, mbeanName);
		RequiredModelMBean modelMBean = null;

		try {
			modelMBean = new RequiredModelMBean(mbeanInfo);
		} catch (RuntimeOperationsException | MBeanException e) {

			e.printStackTrace();
		}

		return modelMBean;

	}

	private ModelMBeanInfo createModelMBeanInfo(ObjectName objectName, String mbeanName) {
		ModelMBeanInfo mBeanInfo = null;

		ModelMBeanAttributeInfo[] attributes = new ModelMBeanAttributeInfo[1];
		ModelMBeanOperationInfo[] operations = new ModelMBeanOperationInfo[3];

		attributes[0] = new ModelMBeanAttributeInfo("Color", "java.lang.String", "the color.", true, true, false, null);
		operations[0] = new ModelMBeanOperationInfo("drive", "the drive method", null, "void", MBeanOperationInfo.ACTION,null);
		operations[1] = new ModelMBeanOperationInfo("getColor", "fs", null, "java.lang.String", MBeanOperationInfo.ACTION,null);
		
		Descriptor setColorDesc = new DescriptorSupport(new String[]{
				"name=setColor","descriptorType=operation","class=" + MANAGED_CLASS_NAME
				,"role=operation"});
		MBeanParameterInfo[] setColorParams = new MBeanParameterInfo[]{
				(new MBeanParameterInfo("new color", "java.lang.String", "fsaf"))
		};
		
		operations[2] = new ModelMBeanOperationInfo("setColor", "fs", setColorParams, "void", MBeanOperationInfo.ACTION,setColorDesc);

		mBeanInfo = new ModelMBeanInfoSupport(MANAGED_CLASS_NAME,null,attributes,null,operations,null);
		return mBeanInfo;
	}
	public static void main(String[] args) {
		ModelAgent modelAgent = new ModelAgent();
		MBeanServer mBeanServer = modelAgent.getmBeanServer();
		
		Car car = new Car();
		
		String defaultDomain = mBeanServer.getDefaultDomain();
		System.out.println(defaultDomain);
		String managerResourceClassName = Car.class.getName();
		System.out.println(managerResourceClassName);
		
		
		ObjectName createObjectName = modelAgent.createObjectName(defaultDomain+":type="+managerResourceClassName);
		ModelMBean createMBean = modelAgent.createMBean(createObjectName, "mybean");
		
		try {
			createMBean.setManagedResource(car, "ObjectReference");
			mBeanServer.registerMBean(createMBean, createObjectName);
		} catch (RuntimeOperationsException | InstanceNotFoundException | MBeanException
				| InvalidTargetObjectTypeException | InstanceAlreadyExistsException | NotCompliantMBeanException e) {
			
			e.printStackTrace();
		}
		
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
