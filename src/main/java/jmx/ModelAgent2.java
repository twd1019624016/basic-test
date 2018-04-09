package jmx;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

import org.apache.commons.modeler.ManagedBean;
import org.apache.commons.modeler.Registry;

public class ModelAgent2 {
	private Registry registry;
	private MBeanServer mBeanServer;

	public ModelAgent2() {
		try {
			registry = createRegistry();
		} catch (Exception e) {

			e.printStackTrace();
		}
		mBeanServer = Registry.getServer();
	}

	@SuppressWarnings("deprecation")
	private Registry createRegistry() throws Exception {
		Registry registry = null;

		URL url = ModelAgent2.class.getResource("mbean.xml");
		InputStream openStream = url.openStream();

		Registry.loadRegistry(openStream);
		openStream.close();

		registry = Registry.getRegistry();

		return registry;
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

	private ModelMBean createMBean(String mbeanName) {

		ManagedBean findManagedBean = registry.findManagedBean(mbeanName);
		if (findManagedBean == null) {
			return null;
		}
		ModelMBean createMBean = null;
		try {
			createMBean = findManagedBean.createMBean();
		} catch (InstanceNotFoundException | RuntimeOperationsException | MBeanException
				| InvalidTargetObjectTypeException e) {

			e.printStackTrace();
		}
		return createMBean;

	}

	public static void main(String[] args) {
		ModelAgent2 modelAgent = new ModelAgent2();
		MBeanServer mBeanServer = modelAgent.getmBeanServer();

		Car car = new Car();

		String defaultDomain = mBeanServer.getDefaultDomain();
		String managerResourceClassName = Car.class.getName();

		ObjectName createObjectName = modelAgent.createObjectName(defaultDomain + ":type=" + managerResourceClassName);

		ModelMBean createMBean = modelAgent.createMBean("mybean");
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
