package xyz.supermoonie.wait;


import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.exception.WebViewClientException;
import xyz.supermoonie.exception.WebViewConnectException;
import xyz.supermoonie.expection.ExpectedCondition;

import java.io.IOException;

/**
 * @author moonie
 * @date 2018/5/1
 */
public class Wait{

    private int timeOut;
    private int interval;
    private WebViewDriver driver;

    public Wait(WebViewDriver driver) {
        this(driver, 10000, 300);
    }

    public Wait(WebViewDriver driver, int timeOut, int interval) {
        this.timeOut = timeOut;
        this.interval = interval;
        this.driver = driver;
    }

    public <V> V until(ExpectedCondition<V> condition) {
        long end = System.currentTimeMillis() + timeOut;
        do {
            try {
                Thread.sleep(interval);
                V value = condition.apply(driver);
                if (null != value) {
                    return value;
                }
            } catch (Exception e) {
                if (e instanceof IOException) {
                    throw new WebViewConnectException(e.getMessage(), e);
                }
            }
            if (System.currentTimeMillis() > end) {
                throw new WebViewClientException("time out");
            }
        } while (true);
    }

}
