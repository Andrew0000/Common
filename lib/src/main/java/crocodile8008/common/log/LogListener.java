package crocodile8008.common.log;

/**
 * Created by Andrei Riik in 10.05.2017.
 */

@SuppressWarnings("WeakerAccess")
public interface LogListener {

    void v(String s);

    void d(String s);

    void i(String s);

    void w(String s);

    void e(String s);

    void e(String s, Throwable throwable);
}
