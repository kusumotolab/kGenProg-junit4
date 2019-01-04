package org.junit.experimental;

import java.util.concurrent.TimeUnit;

public class KgpGlobalConfig {

    public static long timeout;

    public static TimeUnit timeUnit;

    static {
        timeout = 0;
        timeUnit = TimeUnit.SECONDS;
    }
}
