package concurrency;

import java.util.Timer;

public class OutOfTime {
	
	public static void main(String[] args) throws InterruptedException {
		
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		
		Thread.sleep(1000);
		
		timer.schedule(new ThrowTask(), 1);
		
		Thread.sleep(5000);
	}

}
