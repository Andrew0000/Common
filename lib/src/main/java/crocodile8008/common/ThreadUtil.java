package crocodile8008.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * Created by Andrei Riik in 09.05.2017.
 */

@SuppressWarnings("unused")
public class ThreadUtil {

    /**
     * @return {@code true} if current thread is main, {@code false} otherwise.
     */
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Post given task in ui thread handler.
     */
    public static void postOnUi(@NonNull Runnable task) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(task);
    }
}
