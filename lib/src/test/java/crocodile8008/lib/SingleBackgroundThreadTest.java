package crocodile8008.lib;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.*;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import crocodile8008.common.SingleBackgroundThread;
import crocodile8008.common.SingleBackgroundThread.ExceptionCallback;

/**
 * Tests for {@link SingleBackgroundThread}
 */

public class SingleBackgroundThreadTest {

    private Throwable[] exceptionCalled;
    private CountDownLatch latch;
    private ExceptionCallback rememberExceptionAndUnlockMainThread;
    private final RuntimeException runtimeException = new RuntimeException();
    private final Runnable throwRuntimeRunnable = new Runnable() {
        @Override
        public void run() {
            throw runtimeException;
        }
    };

    @Before
    public void setUp() {
        exceptionCalled = new Throwable[1];
        latch = new CountDownLatch(1);

        rememberExceptionAndUnlockMainThread = new ExceptionCallback() {
            @Override
            public void onException(Throwable t) {
                exceptionCalled[0] = t;
                latch.countDown();
            }
        };
    }

    @Test
    public void callbackCalledOnException() throws Exception {
        SingleBackgroundThread thread = new SingleBackgroundThread();
        thread.submit(throwRuntimeRunnable, rememberExceptionAndUnlockMainThread);
        latch.await(1, TimeUnit.SECONDS);
        Assert.assertEquals(runtimeException, exceptionCalled[0]);
    }

    @Test
    public void callbackCalledAfterShutdown() throws Exception {
        SingleBackgroundThread thread = new SingleBackgroundThread();
        thread.shutdown();
        thread.submit(throwRuntimeRunnable, rememberExceptionAndUnlockMainThread);
        latch.await(1, TimeUnit.SECONDS);
        Assert.assertTrue(exceptionCalled[0] instanceof RejectedExecutionException);
    }

    @Test
    public void futureBlocksMainThreadWhenRunnablePosted() throws Exception {
        final int[] result = new int[1];
        Future<?> task = new SingleBackgroundThread().submit(new Runnable() {
            @Override
            public void run() {
                result[0] = 42;
            }
        }, null);
        Assume.assumeThat(result[0], is(0));
        // blocks invoker thread until work done
        task.get();
        Assert.assertEquals(42, result[0]);
    }

    @Test
    public void futureBlocksMainThreadWhenCallablePosted() throws Exception {
        Future<Integer> task = new SingleBackgroundThread().submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 45;
            }
        });
        Assert.assertEquals(45, (int) task.get());
    }
}
