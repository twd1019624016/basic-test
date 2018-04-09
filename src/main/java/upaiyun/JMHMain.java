package upaiyun;


import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHMain {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(".*"+ccc.class.getSimpleName()+".*").forks(1).build();

		new Runner(opt).run();
	}

	

}
