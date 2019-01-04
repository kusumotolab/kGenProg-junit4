package org.junit.experimental;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * タイムアウト指定を保持するBeanクラス．
 * ThreadIdをキーとしてタイムアウト指定を記録する一種のグローバル変数．
 *
 * 本来，タイムアウト指定オブジェクト（KgpDuration）をパラメタとして受け渡すべきだが，
 * パラメタ指定の根が深く，その改変は容易ではない．
 * 受け渡しの流れ： JUnitCore → Computer → ... → JUnit4Builder → BlockJUnit4ClassRunner
 *
 * よって，ThreadIdをキーと値をset/getすることで受け渡しを簡略化．
 * 偶然，同じThreadIdになった場合の挙動が怪しいが，確率はかなり低いはず．
 *
 * @author shinsuke
 *
 */
public class KgpGlobalConfig {

    private static Map<Long, KgpDuration> timeoutMap;

    static {
        timeoutMap = new HashMap<>();
    }

    public static void setTimeout(long timeout, TimeUnit timeUnit) {
        final long currentThreadId = Thread.currentThread().getId();
        final KgpDuration kgpDuration = new KgpDuration(timeout, timeUnit);
        timeoutMap.put(currentThreadId, kgpDuration);
    }

    public static KgpDuration getTimeout() {
        final long currentThreadId = Thread.currentThread().getId();
        if (!timeoutMap.containsKey(currentThreadId)) {
            return KgpDuration.defaultDuration();
        }
        return timeoutMap.get(currentThreadId);
    }

}
