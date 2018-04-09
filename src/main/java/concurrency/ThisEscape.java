package concurrency;

import java.util.List;

/**
 * @author tian
 * this 指针逸出： 只有当构造器函数完成的时候，this引用才能从该线程中逸出，否则是不安全的
 */
public class ThisEscape {

	private int i;
	public ThisEscape(List list) {
		
		Object o  = new Object() {
			private void syso() {
				System.out.println(i);;
			}
		};
		
		list.add(o);
		i = 1;
	}

	
}
