package concurrency;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class UseThisEscape {

	static ArrayList list = new ArrayList<>();

	public static void main(String[] args) {
		Thread thread = new Thread() {

			@Override
			public void run() {
				while (true) {
					for (Object object : list) {
						System.out.println(object);
						Field[] declaredFields = object.getClass().getDeclaredFields();
						for (Field field : declaredFields) {
							field.setAccessible(true);
							try {
								System.out.println(field.getName() + ":" + field.get(object));
								Object outerclazz = field.get(object);

								Field[] outerFields = outerclazz.getClass().getDeclaredFields();
								for (Field outerfield : outerFields) {
									outerfield.setAccessible(true);

									System.out.println(outerfield.getName() + ":" + outerfield.get(outerclazz));
								}
							} catch (IllegalArgumentException | IllegalAccessException e) {

								e.printStackTrace();
							}
						}
					}
				}
			}

		};
		thread.start();
		ThisEscape thisEscape = new ThisEscape(list);

		System.out.println(thisEscape);
	}

}
