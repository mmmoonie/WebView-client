package xyz.supermoonie.loop;

import xyz.supermoonie.command.AbstractCommand;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.exception.WebViewClientException;
import xyz.supermoonie.exception.WebViewConnectException;
import xyz.supermoonie.expection.ExpectedCondition;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;

/**
 *
 * @author wangchao
 * @date 2018/5/7
 */
public class Loop {

    private WebViewDriver driver;
    private Wait wait;
    private int retryTimes;

    public Loop(WebViewDriver driver, Wait wait) {
        this(driver, wait, 3);
    }

    public Loop(WebViewDriver driver, Wait wait, int retryTimes) {
        this.driver = driver;
        this.wait = wait;
        this.retryTimes = retryTimes;
    }

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
}
