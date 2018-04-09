package javabeanTest;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

import net.sf.cglib.beans.BeanCopier;

public class BasicTest {
	 class JavaPerson {
		String name;
		double salary;
		Date birthday;
		Integer age;

		public String getName() {
			return name;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "JavaPerson [name=" + name + ", salary=" + salary + ", birthday=" + birthday + ", age=" + age + "]";
		}
		

	}
	
 class JavaPersonTarget {
		String name;
		double salary;
		Date birthday;
		Long age;

		public String getName() {
			return name;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		

		public Long getAge() {
			return age;
		}

	public void setAge(Long age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "JavaPersonTarget [name=" + name + ", salary=" + salary + ", birthday=" + birthday + ", age=" + age + "]";
		}
		

	}

	public static void main(String[] args) throws IntrospectionException {
		@SuppressWarnings("unused")

		BeanInfo beanInfo = Introspector.getBeanInfo(JavaPerson.class);

		System.out.println("54444444444");
	}

	@Test
	public void beanutilsTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		JavaPerson sources = new JavaPerson();
		sources.setAge(23);
		sources.setName("zhang");
		sources.setSalary(33.33d);

		JavaPersonTarget destination = new JavaPersonTarget();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(destination, sources);

	/*	PropertyUtils.copyProperties(destination, sources);*/ ///不能进行类型转换，BeanUtilsBean 在 PropertyUtilsBean 的基础上 添加了类型转换，可以自定义转换器，默认 date 和 bigDecimal 转换器会对空值报错
		System.out.println(destination);

	}

	@Test
	public void beanCopierTest() {

		JavaPerson sources = new JavaPerson();
		sources.setAge(23); 
		sources.setName("zhang");
		sources.setSalary(33.33d);

		JavaPersonTarget target = new JavaPersonTarget();

		BeanCopier beanCopier = BeanCopier.create(sources.getClass(), target.getClass(), false);

		beanCopier.copy(sources, target, null);

		System.out.println(target);
	}
}
