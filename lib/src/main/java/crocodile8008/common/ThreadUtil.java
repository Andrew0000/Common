package crocodile8008.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * Created by Andrei Riik in 09.05.2017.
 */

public class ThreadUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void postOnUi(@NonNull Runnable task, @NonNull Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(task);
    }
}
