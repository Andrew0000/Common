package crocodile8008.lib;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import crocodile8008.common.ThrowIfAllowed;

/**
 * Tests for {@link ThrowIfAllowed}
 */
@RunWith(AndroidJUnit4.class)
public class ThrowIfAllowedTest {

    @Test
    public void notThrownByDefault() throws Exception {
        ThrowIfAllowed.runtime(new RuntimeException());
    }

    @Test(expected = RuntimeException.class)
    public void thrownIfEnabled() throws Exception {
        ThrowIfAllowed.setFailAllowed(true);
        ThrowIfAllowed.runtime(new RuntimeException());
    }

    @Test
    public void notThrownIfDisabled() throws Exception {
        ThrowIfAllowed.setFailAllowed(false);
        ThrowIfAllowed.runtime(new RuntimeException());
    }
}
