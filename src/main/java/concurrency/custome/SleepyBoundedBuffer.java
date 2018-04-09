package concurrency.custome;

/**
 * @author tian
 *
 * @param <V>
 * 
 * 通过轮询与休眠来实现简单的阻塞
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected SleepyBoundedBuffer(int capacity) {
		super(capacity);
		
	}
	
	public void put(V v) throws InterruptedException {
		while(true)	{
			synchronized (this) {
				if(!isFull()) {
					doput(v);
					return;
			}
			Thread.sleep(10);	
			}
		}
	}
	
	public V take()	throws InterruptedException {
		while(true) {
			synchronized (this) {
				if(!isEmpty()) {
					return doTake();
				}
			}
			
			Thread.sleep(10);
		}
	}

}
