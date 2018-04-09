
package upaiyun;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
@SuppressWarnings("restriction")
public class UnsafeUtil {

   
	private static Unsafe tryUnsafe() {
        return AccessController.doPrivileged(
                new PrivilegedAction<Unsafe>() {
                    public Unsafe run() {
                        try {
                            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
                            unsafe.setAccessible(true);
                            return (Unsafe) unsafe.get(null);
                        } catch (NoSuchFieldException e) {
                            throw new IllegalStateException(e);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                }
        );
    }

    private static Unsafe INSTANCE;

    public static Unsafe current() {
        if (INSTANCE != null) return INSTANCE;

        Unsafe u = tryUnsafe();
        INSTANCE = u;
        if (u == null) {
            throw new IllegalStateException("Unsafe is not available.");
        }


        return INSTANCE;
    }

}
