package concurrency.custome;


/**
 * @author tian
 *
 * @param <V>
 * 
 * 将前提失败条件传给调用者
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	public  GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}

	public synchronized void put(V v) throws Exception {
		if(isFull()) {
			throw new RuntimeException();
		}
		
		doput(v);
	}
	
	public synchronized V take() throws Exception{
		if(isEmpty()) {
			throw new RuntimeException();
		}
		
		return doTake();
	}
}
