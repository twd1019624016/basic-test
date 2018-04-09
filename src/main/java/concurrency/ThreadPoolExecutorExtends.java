package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExtends extends ThreadPoolExecutor{

	public ThreadPoolExecutorExtends(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		
	}

	@Override
	protected void finalize() {
		
		super.finalize();
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		
		super.afterExecute(r, t);
	}

	@Override
	protected void terminated() {
		
		super.terminated();
	}

	
}
