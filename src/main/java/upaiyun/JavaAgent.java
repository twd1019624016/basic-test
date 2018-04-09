package upaiyun;

import java.lang.instrument.Instrumentation;

public class JavaAgent {

	private static Instrumentation instrumention;
	
	public static void premain(String args,Instrumentation inst) {
		instrumention = inst;
	}

	public static Instrumentation getInstrumention() {
		return instrumention;
	}

	
}
