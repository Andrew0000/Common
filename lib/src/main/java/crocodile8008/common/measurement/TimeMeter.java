package crocodile8008.common.measurement;

import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei Riik in 11.05.2017.
 */

@SuppressWarnings("unused, WeakerAccess")
public class TimeMeter {

    private static final String TAG = TimeMeter.class.getSimpleName();
    private static final Map<String, Long> inProgress = new HashMap<>();

    /**
     * Like {@link Trace#beginSection(String)}
     */
    public static void beginSection(@NonNull String sectionName) {
        inProgress.put(sectionName, nowMillis());
    }

    /**
     * See {@link #logCurrent(String, String)}
     */
    public static void logCurrent(@NonNull String sectionName) {
        logCurrent(sectionName, null);
    }

    /**
     * Log current time diff for given key if exist.
     */
    public static void logCurrent(@NonNull String sectionName, @Nullable String label) {
        Long start = inProgress.get(sectionName);
        if (start != null) {
            String time = String.valueOf(nowMillis() - start);
            Log.d(TAG, sectionName + ": " + (label == null ? time : label + " " + time));
        }
    }

    /**
     * Like {@link Trace#endSection()}.
     * Remove given key from storage and print time diff.
     */
    public static void endSection(@NonNull String sectionName) {
        Long start = inProgress.remove(sectionName);
        if (start != null) {
            String time = String.valueOf(nowMillis() - start);
            Log.d(TAG, sectionName + ": " + time);
        }
    }

    private static long nowMillis() {
        return System.currentTimeMillis();
    }
}
