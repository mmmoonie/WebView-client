package xyz.supermoonie.loop;

import xyz.supermoonie.command.AbstractCommand;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.exception.WebViewClientException;
import xyz.supermoonie.exception.WebViewConnectException;
import xyz.supermoonie.expection.ExpectedCondition;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;

/**
 * 循环器，针对的是一些列操作
 * 依赖于 {@link Wait} 当 Wait 返回值不为 null 时，停止循环
 *
 * @author wangchao
 * @date 2018/5/7
 */
public class Loop {

    /**
     * WebViewSpider 驱动
     */
    private WebViewDriver driver;
    /**
     * 等待
     */
    private Wait wait;
    /**
     * 重试的最大次数
     */
    private int retryTimes;

    public Loop(WebViewDriver driver, Wait wait) {
        this(driver, wait, 3);
    }

    public Loop(WebViewDriver driver, Wait wait, int retryTimes) {
        this.driver = driver;
        this.wait = wait;
        this.retryTimes = retryTimes;
    }

    /**
     * 开始循环
     *
     * @param command       指令
     * @param condition     指令执行后期望返回值达到某种条件
     * @param <T>           结果类型
     * @return              结果
     */
    public <T> T begin(final AbstractCommand command, ExpectedCondition<T> condition) {
        for (int i = 0; i < retryTimes; i ++) {
            try {
                driver.sendCommand(command);
                T t = wait.until(condition);
                if (null != t) {
                    return t;
                }
            } catch (IOException e) {
                if (i >= retryTimes -1) {
                    throw new WebViewConnectException(e.getMessage(), e);
                }
            } catch (WebViewClientException ignore) {

            }
        }
        throw new WebViewClientException(retryTimes + " times has been used, but no result return");
    }

    public WebViewDriver getDriver() {
        return driver;
    }

    public Wait getWait() {
        return wait;
    }

    public void setWait(Wait wait) {
        this.wait = wait;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }
}
