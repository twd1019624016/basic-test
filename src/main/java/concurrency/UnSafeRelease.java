package concurrency;


public class UnSafeRelease {

	
	public Holder holder;

	public void initialize() {
		holder = new Holder(42);
	}
}

class Holder {
	private int n;

	public Holder(int n) {
		System.out.println(11);
		this.n = n;
	}

	public void assertSanity()	{
		if(n != n ) {
			throw new RuntimeException("This statament is false");
		}
	}
	
}