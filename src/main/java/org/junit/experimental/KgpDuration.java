package org.junit.experimental;

import java.util.concurrent.TimeUnit;

public class KgpDuration {
    public long timeout;
    public TimeUnit timeUnit;

    public KgpDuration(long timeout, TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public static KgpDuration defaultDuration() {
        return new KgpDuration(0, TimeUnit.SECONDS);
    }
}