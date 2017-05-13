package crocodile8008.lib;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

import crocodile8008.common.SerializationUtils;

/**
 * Tests for {@link SerializationUtils}
 */
public class SerializationUtilTest {

    @Test
    public void writesAndReads() throws Exception {
        SerializationTestClass object = new SerializationTestClass();
        object.a = 1;
        object.b = 2.5f;
        object.c = "String value";
        byte[] bytes = SerializationUtils.toBytes(object);

        SerializationTestClass restored = (SerializationTestClass) SerializationUtils.toObject(bytes);
        Assert.assertEquals(object, restored);
    }

    private static class SerializationTestClass implements Serializable {
        int a;
        float b;
        String c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SerializationTestClass that = (SerializationTestClass) o;

            if (a != that.a) return false;
            if (Float.compare(that.b, b) != 0) return false;
            return c != null ? c.equals(that.c) : that.c == null;
        }
    }
}
