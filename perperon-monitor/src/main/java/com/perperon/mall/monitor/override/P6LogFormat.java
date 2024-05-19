package com.perperon.mall.monitor.override;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.SingleLineFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author perperon
 * @date 2024/5/19
 * @apiNote
 */
public class P6LogFormat extends SingleLineFormat {
    private Logger logger = LoggerFactory.getLogger(P6LogFormat.class);

    public P6LogFormat() {
    }

    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        Thread t = Thread.currentThread();
        String log = now + "|" + t.getName() + "|" + elapsed + "|" + category + "|connection " + connectionId + "|\nAfter Prepared SQL:" + SQLFormatter.format(P6Util.singleLine(prepared)) + "\nBefore Prepared SQL:" + SQLFormatter.format(P6Util.singleLine(sql));
        this.logger.info(log);
        return log;
    }
}
