package concurrency;

import java.lang.reflect.Field;

public class UseUnsafeRelease {

	public static void main(String[] args) {
		UnSafeRelease release = new UnSafeRelease();
		
		Thread thread = new Thread() {

			@Override
			public void run() {
				while (true) {
					if(release.holder != null) {
						release.holder.assertSanity();
					}
				}
			}
		};
		thread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		release.initialize();
	}
}
