package crocodile8008.common.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

@SuppressWarnings("unused, WeakerAccess")
public class Lo {

    private static String tag = "Logger";
    private static boolean enabled;
    @Nullable private static LogListener externalListener;

    public static void init(boolean enabled, @NonNull String tag) {
        Lo.enabled = enabled;
        Lo.tag = tag;
    }

    public void setListener(@NonNull LogListener logListener) {
        externalListener = logListener;
    }

    public void removeListener() {
        externalListener = null;
    }

    public static void v(String s) {
        if (enabled) {
            Log.v(tag, s);

            final LogListener listener = externalListener;
            if (listener != null) {
                listener.v(s);
            }
        }
    }

    public static void d(String s) {
        if (enabled) {
            Log.d(tag, s);

            final LogListener listener = externalListener;
            if (listener != null) {
                listener.d(s);
            }
        }
    }

    public static void i(String s) {
        if (enabled) {
            Log.i(tag, s);

            final LogListener listener = externalListener;
            if (listener != null) {
                listener.i(s);
            }
        }
    }

    public static void w(String s) {
        if (enabled) {
            Log.w(tag, s);

            final LogListener listener = externalListener;
            if (listener != null) {
                listener.w(s);
            }
        }
    }

    public static void e(String s) {
        if (enabled) {
            Log.e(tag, s);

            final LogListener listener = externalListener;
            if (listener != null) {
                listener.e(s);
            }
        }
    }

    public static void e(String s, Throwable throwable) {
        if (enabled) {
            Log.e(tag, s, throwable);

            final LogListener listener = externalListener;
            if (listener != null) {
                listener.e(s, throwable);
            }
        }
    }
}
