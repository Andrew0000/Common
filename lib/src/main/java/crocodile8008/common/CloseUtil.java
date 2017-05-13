package crocodile8008.common;

import android.support.annotation.Nullable;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Andrei Riik in 2017.
 */

@SuppressWarnings("unused, WeakerAccess")
public class CloseUtil {

    /**
     * Close given object and suppress {@link IOException} if thrown.
     */
    public static void closeSilently(@Nullable Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            // nothing needed
        }
    }

    /**
     * Close given object and suppress {@link IOException} if thrown.
     */
    public static void closeSilently(@Nullable ObjectInput objectInput) {
        if (objectInput == null) {
            return;
        }
        try {
            objectInput.close();
        } catch (IOException e) {
            // nothing needed
        }
    }

    /**
     * Close given object and suppress {@link IOException} if thrown.
     */
    public static void closeSilently(@Nullable ObjectOutput objectInput) {
        if (objectInput == null) {
            return;
        }
        try {
            objectInput.close();
        } catch (IOException e) {
            // nothing needed
        }
    }

    /**
     * Flush given object and suppress {@link IOException} if thrown.
     */
    public static void flushSilently(@Nullable Flushable flushable) {
        if (flushable == null) {
            return;
        }
        try {
            flushable.flush();
        } catch (IOException e) {
            // nothing needed
        }
    }
}
