package crocodile8008.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Random;

import crocodile8008.common.FileReaderWriter;

/**
 * Tests for {@link FileReaderWriter}
 */
@RunWith(AndroidJUnit4.class)
public class FileReaderWriterTest {

    @Test
    public void fileWritesAndReadsManyTimes() throws Exception {
        String fileName = getAppContext().getCacheDir() + "/test_file_1";
        File file = new File(fileName);

        byte[] bytes1 = createBytes(12567);
        FileReaderWriter.writeToDisc(bytes1, file);
        byte[] read1 = FileReaderWriter.readFromDisc(file);
        Assert.assertArrayEquals(bytes1, read1);

        byte[] bytes2 = createBytes(3453239);
        FileReaderWriter.writeToDisc(bytes2, file);
        byte[] read2 = FileReaderWriter.readFromDisc(file);
        Assert.assertArrayEquals(bytes2, read2);
    }

    @Test
    public void stringWritesAndReadsManyTimes() throws Exception {
        String fileName = getAppContext().getCacheDir() + "/test_file_2";
        File file = new File(fileName);

        String string1 = "First string";
        FileReaderWriter.writeString(string1, file);
        String read1 = FileReaderWriter.readString(file);
        Assert.assertEquals(string1, read1);

        String string2 = "Second string";
        FileReaderWriter.writeString(string2, file);
        String read2 = FileReaderWriter.readString(file);
        Assert.assertEquals(string2, read2);
    }

    private Context getAppContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @NonNull
    private byte[] createBytes(int length) {
        byte[] bytes = new byte[length];
        Random random = new Random();
        random.nextBytes(bytes);
        return bytes;
    }
}
