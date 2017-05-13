package crocodile8008.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by Andrei Riik in 2017.
 */

@SuppressWarnings("unused, WeakerAccess")
public class SerializationUtils {

    @Nullable
    public static byte[] toBytes(@NonNull Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
            return bos.toByteArray();
        } finally {
            CloseUtil.closeSilently(out);
            CloseUtil.closeSilently(bos);
        }
    }

    @Nullable
    public static Object toObject(@NonNull byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        Object result = null;
        try {
            in = new ObjectInputStream(bis);
            return in.readObject();
        } finally {
            CloseUtil.closeSilently(in);
            CloseUtil.closeSilently(bis);
        }
    }
}
