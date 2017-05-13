package crocodile8008.common.threads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/**
 * Handy class with single background thread executor.
 *
 * Created by Andrei Riik in 2017.
 */

@SuppressWarnings("unused, WeakerAccess")
public class SingleBackgroundThread {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Just like {@link ExecutorService#submit(Callable)}
     */
    @NonNull
    public <T> Future<T> submit(@NonNull final Callable<T> callable) {
        return executor.submit(callable);
    }

    /**
     * Like {@link ExecutorService#submit(Runnable)} with additional exception callback.
     */
    @Nullable
    public Future<?> submit(@NonNull final Runnable runnable, @Nullable final ExceptionCallback exceptionCallback) {
        try {
            return trySubmit(runnable, exceptionCallback);
        } catch (RejectedExecutionException e) {
            tryInvokeExceptionCallback(e, exceptionCallback);
            return null;
        }
    }

    @NonNull
    private Future<?> trySubmit(@NonNull final Runnable runnable,@Nullable final ExceptionCallback exceptionCallback) {
        return executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable t) {
                    // ExecutorService may blocks all exceptions inside
                    tryInvokeExceptionCallback(t, exceptionCallback);
                }
            }
        });
    }

    private void tryInvokeExceptionCallback(@NonNull Throwable t, @Nullable ExceptionCallback exceptionCallback) {
        if (exceptionCallback != null) {
            try {
                exceptionCallback.onException(t);
            } catch (Throwable t2) {
                // nothing more needed
            }
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

    public interface ExceptionCallback {
        void onException(Throwable t);
    }
}
