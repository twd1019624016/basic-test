package concurrency.custome;

/**
 * @author tian
 *
 * @param <V>
 *            使用内部条件队列 实现阻塞
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected BoundedBuffer(int capacity) {
		super(capacity);

	}

	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) {
			wait();
		}

		boolean wasEmpty = isEmpty();
		doput(v);
		if (wasEmpty)
			notifyAll();
	}

	public synchronized V take() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}
		boolean wasFull = isFull();
		V doTake = doTake();
		if (wasFull)
			notifyAll();

		return doTake;
	}

}
