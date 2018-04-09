package upaiyun;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.misc.Unsafe;


public class InStruMentTest {

	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		Instrumentation instr = JavaAgent.getInstrumention();
	    System.out.println(instr.getObjectSize(new Object())); 
	    
	    Unsafe current = UnsafeUtil.current();
	    int addressSize = current.addressSize();
	    System.out.println(addressSize);
	    
	    
		System.	out.println(VM.current().details());
		System.  out.println(ClassLayout.parseClass(Object.class).toPrintable());
	}
	
}
