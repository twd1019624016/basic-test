package javabeanTest;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.beans.BulkBean;
import net.sf.cglib.beans.ImmutableBean;
import net.sf.cglib.core.Converter;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

public class BeanCopierTest {
    
    @Test
	public void testSimple() {
		BeanCopier beanCopier = BeanCopier.create(Bean.class, Bean.class, false);
		
		Bean from = new Bean();
		from.setFunction("测试的bean");
		
		Bean to = new Bean();
		
		beanCopier.copy(from, to, null);
		
		System.out.println(to.getFunction());
	}
    
    @SuppressWarnings("unused")
	@Test
   	public void testConvert() {
    	StringBuilder builder = new StringBuilder("C:");
    	builder.append(File.separatorChar)
    		.append("Users")
    		.append(File.separatorChar)
    		.append("tian")
    		.append(File.separatorChar)
    		.append("Desktop")
    		.append(File.separatorChar)
    		.append("cglclass");
    		
    	System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,builder.toString()); // 生成字节码文件
   		BeanCopier beanCopier = BeanCopier.create(Bean.class, Bean.class, true);
   		
   		Bean from = new Bean();
   		from.setFunction("测试的bean33");
   		
   		Bean to = new Bean();
   		
   		beanCopier.copy(from, to, new Converter() {
   		 /**
             * @param sourceValue source对象属性值
             * @param targetClass target对象对应类
             * @param methodName targetClass里属性对应set方法名,eg.setId
             * @return
             */
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Object convert(Object sourceValue, Class targetClass, Object methodName ) {
				if(targetClass.isAssignableFrom(String.class)) {
					
					return "hehehe";
				}
				return sourceValue;
			}
		});
   		
   		System.out.println(to.getFunction());
   		
   		Bean from2 = new Bean();
   		from2.setFunction("测试的bulkBean");
   		from2.setUseWay("猜猜bulkBean");
   		BulkBean bulkBean = BulkBean.create(Bean.class, new String[]{"getFunction","getUseWay"}, new String[]{"setFunction","setUseWay"},new Class[]{ String.class,String.class});
    
   		Object[] propertyValues = bulkBean.getPropertyValues(from2);
   		Bean to2 = new Bean();
   		bulkBean.setPropertyValues(to2, propertyValues);
   		
   		System.out.println(to2);
   		
   		BeanMap beanMap = BeanMap.create(from2);
   		
   		beanMap.setBean(from2);
   		
   		Set keySet = beanMap.keySet();
   		for (Object object : keySet) {
			System.out.println(object);
		}
   		Map t = new HashMap();
   		t.put("useWay", "1");
   		t.put("function", "2");
   		t.put("age", 23);
   		beanMap.putAll(t);
   		
   		Object bean = beanMap.getBean();
   		
   		System.out.println(bean);
   		
   		BeanGenerator generator = new BeanGenerator();  
   		generator.addProperty("intValue", int.class);  
   		generator.addProperty("integer", Integer.class);  
   		generator.addProperty("properties", Properties.class);  
   		         
   		Class clazz = (Class) generator.createClass();  
   		Object obj = generator.create(); 
   		
   		  
   		PropertyDescriptor[] getters = ReflectUtils.getBeanGetters(obj.getClass());  
   		for (PropertyDescriptor getter : getters) {  
   		    Method write = getter.getWriteMethod();  
   		    System.out.println(write.getName());  
   		}  
   		
   		Object create = ImmutableBean.create(from2);
   		
   		
   		
   		
    }
    
    @Test
    public void testFast() throws InvocationTargetException {
    	StringBuilder builder = new StringBuilder("C:");
    	builder.append(File.separatorChar)
    		.append("Users")
    		.append(File.separatorChar)
    		.append("tian")
    		.append(File.separatorChar)
    		.append("Desktop")
    		.append(File.separatorChar)
    		.append("cglfast");
    	System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,builder.toString()); // 生成字节码文件

    	FastClass fastClass = FastClass.create(Bean.class);
    	
    	Bean from2 = new Bean();
   		from2.setFunction("测试的fast");
   		from2.setUseWay("猜猜fast");
    	Object invoke = fastClass.invoke("getFunction", new Class[]{}, from2, new Object[]{});
       System.out.println(invoke);
       
       FastMethod fastMethod = fastClass.getMethod("getFunction", new Class[]{});
       
       int index = fastMethod.getIndex();
       Object invoke2 = fastMethod.invoke(from2, new Object[]{});
       
       System.out.println(index);
       System.out.println(invoke2);
       
       
      
    
    }
    
    @Test
    public void testEnhancer() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	StringBuilder builder = new StringBuilder("C:");
    	builder.append(File.separatorChar)
    		.append("Users")
    		.append(File.separatorChar)
    		.append("tian")
    		.append(File.separatorChar)
    		.append("Desktop")
    		.append(File.separatorChar)
    		.append("cglenhancer");
    	System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,builder.toString()); // 生成字节码文件

    	 Enhancer enhancer = new Enhancer();
    	 
    	 enhancer.setSuperclass(Bean.class);
    	 enhancer.setCallback(new MethodInterceptor() {
 			
 			@Override
 			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
 				System.out.println(method + "intercepted");
 				proxy.invokeSuper(obj, args);
 				return null;
 			}
 		});
    	 Bean create = (Bean) enhancer.create();
    	 
    	
    	 create.getAge();
    	 
    	 create.setAge(1);
    	 
    	 InterfaceMaker interfaceMaker = new InterfaceMaker();
    	 
    	 interfaceMaker.add(Bean.class);
    	 
    	 Class interfacess = interfaceMaker.create();
    	 
    	 Method[] methods = interfacess.getMethods();
    	 
    	 for (Method method : methods) {
			System.out.println(method.getName());
		}
    	 
    	 
    	 Object create2 = Enhancer.create(Object.class, new Class[]{interfacess},new MethodInterceptor() {
  			
  			@Override
  			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
  				System.out.println(method + "intercepted");
  				Object invokeSuper = proxy.invokeSuper(obj, args);
  				System.out.println(invokeSuper);
  				return null;
  			}
  		});
    	 create2.toString();
    	 
    	 Method targetMethod1=create2.getClass().getMethod("getName");  
        Object invoke = targetMethod1.invoke(create2); 
        System.out.println(invoke);
    }
}
