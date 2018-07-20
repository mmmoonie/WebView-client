package xyz.supermoonie.wait;


import xyz.supermoonie.command.AbstractCommand;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.exception.WebViewClientException;
import xyz.supermoonie.exception.WebViewConnectException;
import xyz.supermoonie.expection.ExpectedCondition;

import java.io.IOException;

/**
 * 等待，针对的是单个命令
 * 当满足期望的条件时，则返回相应的数据
 * 当不满足期望的条件时，则重试直到超时
 *
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

    /**
     * 等待，直到达到某种条件
     *
     * @param condition     期望达到的条件
     * @param <V>           结果类型
     * @return              结果
     */
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

    /**
     * 等待，直到达到某种条件
     *
     * @param command       首先执行的命令
     * @param condition     期望达到的条件
     * @param <V>           结果类型
     * @return              结果
     */
    public <V> V until(AbstractCommand command, ExpectedCondition<V> condition) throws IOException {
        driver.sendCommand(command);
        return until(condition);
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public WebViewDriver getDriver() {
        return driver;
    }
}
