package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.elasticsearch.common.util.concurrent.AtomicArray;

public class SynchroContainer {
		
	    private static final int COUNT_BITS = Integer.SIZE - 3;
	    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

	    // runState is stored in the high-order bits
	    private static final int RUNNING    = -1 << COUNT_BITS;
	    private static final int SHUTDOWN   =  0 << COUNT_BITS;
	    private static final int STOP       =  1 << COUNT_BITS;
	    private static final int TIDYING    =  2 << COUNT_BITS;
	    private static final int TERMINATED =  3 << COUNT_BITS;

	    // Packing and unpacking ctl
	    private static int runStateOf(int c)     { return c & ~CAPACITY; }
	    private static int workerCountOf(int c)  { return c & CAPACITY; }
	    private static int ctlOf(int rs, int wc) { return rs | wc; }
	    
	    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
	    
	    private static  sun.misc.Unsafe UNSAFE;
	    private static  long TID_OFFSET;
	    static {
	        try {
	            UNSAFE = sun.misc.Unsafe.getUnsafe();
	            Class<?> tk = Thread.class;
	            TID_OFFSET = UNSAFE.objectFieldOffset
	                (tk.getDeclaredField("tid"));
	        } catch (Exception e) {
	            throw new Error(e);
	        }
	    }

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		 try {
	            UNSAFE = sun.misc.Unsafe.getUnsafe();
	            Class<?> tk = Thread.class;
	            TID_OFFSET = UNSAFE.objectFieldOffset
	                (tk.getDeclaredField("tid"));
	        } catch (Exception e) {
	            throw new Error(e);
	        }
		/*
		 * ArrayList arrayList = new ArrayList<>();
		 * 
		 * Iterator iterator = arrayList.iterator();
		 * 
		 * ConcurrentHashMap map = new ConcurrentHashMap<>();
		 * 
		 * CopyOnWriteArrayList arrayList2 = new CopyOnWriteArrayList<>();
		 * arrayList2.iterator();
		 * 
		 * ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(12);
		 * arrayBlockingQueue.take(); arrayBlockingQueue.put(e);
		 * 
		 * Object e = new Object(); e.wait();
		 * 
		 * Thread.currentThread().interrupt();
		 * 
		 * SynchronousQueue synchronousQueue = new SynchronousQueue<>();
		 * 
		 * synchronousQueue.put(e);
		 */
		/*
		 * FutureTask<String> futureTask = new
		 * FutureTask<String>(()->"2222222");
		 * 
		 * Thread thread = new Thread(futureTask);
		 * 
		 * thread.start();
		 * 
		 * String string = futureTask.get(); System.out.println(string); String
		 * string2 = futureTask.get(); System.out.println(string2); String
		 * string3 = futureTask.get(); System.out.println(string3);
		 */
/*
		FutureTask<String> futureTask = new FutureTask<String>(() -> {
		}, "444");
		Thread thread = new Thread(futureTask);
		
		thread.start();
		thread.interrupt();
		thread.isInterrupted();
		Thread.interrupted();
		String string = futureTask.get();
		System.out.println(string);
		String string2 = futureTask.get();
		System.out.println(string2);
		String string3 = futureTask.get();
		System.out.println(string3);
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
		ExecutorCompletionService completionService = new ExecutorCompletionService<>(newFixedThreadPool);
		completionService.take();	
		
		newFixedThreadPool.invokeAll();
		
		  ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(12);
		  arrayBlockingQueue.take(); arrayBlockingQueue.put(e);
		  */
	ThreadPoolExecutor newFixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		FutureTask<String> futureTask = new FutureTask<String>(() -> {while(true)System.out.println("线程运行11");
		}, "444");
		futureTask.get();
		  Future<?> submit = newFixedThreadPool.submit(futureTask);
		  boolean cancel = submit.cancel(false);
		  System.out.println(cancel);
		  List<Runnable> shutdownNow = newFixedThreadPool.shutdownNow();
		  
		  ArrayList arrayList = new ArrayList<>(new ArrayList());arrayList.toArray();
		  
		  newFixedThreadPool.prestartAllCoreThreads();
		  
		  newFixedThreadPool.allowCoreThreadTimeOut(true);
		  
		  Executors.newSingleThreadExecutor();
		  newFixedThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		/*  int c = ctl.get();
		  int workerCountOf = workerCountOf(c);*/
		  
		  ReentrantLock lock = new ReentrantLock();
		  lock.lock();
		  lock.tryLock();
		  lock.lockInterruptibly();
		  lock.unlock();
		  Condition newCondition = lock.newCondition();
	}
	
	public void atomicVar() {
		AtomicInteger atomicInteger = new AtomicInteger();
		
		AtomicReferenceFieldUpdater<String, String> next = AtomicReferenceFieldUpdater.newUpdater(String.class, String.class, "fs");
		
		AtomicStampedReference<String> fds = new AtomicStampedReference<String>("fd", 12);
		AtomicMarkableReference<String> dfsf = new AtomicMarkableReference<String>("fsd", true);
	}
}
