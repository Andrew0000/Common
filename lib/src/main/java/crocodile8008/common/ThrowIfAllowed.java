package crocodile8008.common;

import android.support.annotation.NonNull;

/**
 * Created by Andrei Riik in 11.05.2017.
 */

@SuppressWarnings("unused")
public class ThrowIfAllowed {

    private static boolean failEnabled;

    /**
     * Just invoke this with actual BuildConfig.DEBUG value.
     */
    public static void setFailAllowed(boolean failEnabled) {
        ThrowIfAllowed.failEnabled = failEnabled;
    }

    /**
     * Throws given exception if allowed, do nothing otherwise.
     */
    public static void runtime(@NonNull RuntimeException exception) {
        if (failEnabled) {
            throw exception;
        }
    }
}
