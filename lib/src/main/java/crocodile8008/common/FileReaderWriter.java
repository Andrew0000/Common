package crocodile8008.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static crocodile8008.common.CloseUtil.closeSilently;
import static crocodile8008.common.CloseUtil.flushSilently;

/**
 * Created by Andrei Riik in 2017.
 */

@SuppressWarnings("unused, WeakerAccess")
public class FileReaderWriter {

    /**
     * Write given bytes in to given file.
     */
    public static void writeToDisc(@NonNull byte[] bytes, @NonNull File file) throws IOException {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes);
        } finally {
            flushSilently(bos);
            closeSilently(bos);
        }
    }

    /**
     * Read bytes from given file.
     */
    @Nullable
    public static byte[] readFromDisc(@NonNull File file) throws IOException {
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        BufferedInputStream buf = null;
        try {
            buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            return bytes;
        } finally {
            closeSilently(buf);
        }
    }

    /**
     * Write given string in to given file.
     */
    public static void writeString(@NonNull String data, @NonNull File file) throws IOException {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(file));
            osw.write(data);
        } finally {
            flushSilently(osw);
            closeSilently(osw);
        }
    }

    /**
     * Read bytes from given file and return new {@link String} from this bytes or {@code null}.
     */
    @Nullable
    public static String readFullString(@NonNull File file) throws IOException {
        byte[] bytes = readFromDisc(file);
        return bytes == null ? null : new String(bytes);
    }

    /**
     * Read string from given file by lines.
     * Note: any '\n' characters will be lost with this method.
     * See {@link BufferedReader#readLine()}
     */
    @NonNull
    public static String readStringByLines(@NonNull File file) throws IOException {
        InputStream is = null;
        InputStreamReader isReader = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(file);
            isReader = new InputStreamReader(is);
            br = new BufferedReader(isReader);

            StringBuilder stringBuilder = new StringBuilder();
            String readLine;
            while ((readLine = br.readLine()) != null) {
                stringBuilder.append(readLine);
            }
            return stringBuilder.toString();
        } finally {
            closeSilently(is);
            closeSilently(isReader);
            closeSilently(br);
        }
    }
}
