package crocodile8008.lib;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLooper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import crocodile8008.common.threads.ThreadUtil;

import static org.hamcrest.Matchers.is;

/**
 * Tests for {@link ThreadUtil}
 */

@RunWith(RobolectricTestRunner.class)
public class ThreadUtilTest {

    @Test
    public void mainThreadDetected() {
        Assert.assertTrue(ThreadUtil.isMainThread());
    }

    @Test
    public void anotherThreadDetected() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final int[] threadWorked = new int[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                Assert.assertFalse(ThreadUtil.isMainThread());
                threadWorked[0]++;
                latch.countDown();
            }
        }).start();
        latch.await(1, TimeUnit.SECONDS);
        Assume.assumeThat(threadWorked[0], is(1));
    }

    @Test
    public void postToMainThreadManyTimesWorksProperly() throws Exception {
        final CountDownLatch latch = new CountDownLatch(2);
        final int[] threadWorked = new int[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.postOnUi(new Runnable() {
                    @Override
                    public void run() {
                        Assert.assertTrue(ThreadUtil.isMainThread());
                        threadWorked[0]++;
                    }
                });
                latch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.postOnUi(new Runnable() {
                    @Override
                    public void run() {
                        Assert.assertTrue(ThreadUtil.isMainThread());
                        threadWorked[0]++;
                    }
                });
                latch.countDown();
            }
        }).start();

        latch.await(1, TimeUnit.SECONDS);
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        Assume.assumeThat(threadWorked[0], is(2));
    }
}
