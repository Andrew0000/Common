package crocodile8008.common.threads;

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
        FinalHolder.handler.post(task);
    }

    // Initialization-on-demand holder
    private static class FinalHolder {
        static final Handler handler = new Handler(Looper.getMainLooper());
    }
}
