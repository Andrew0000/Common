package crocodile8008.common;

import android.os.Looper;

/**
 * Created by Andrei Riik in 09.05.2017.
 */

public class ThreadUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
