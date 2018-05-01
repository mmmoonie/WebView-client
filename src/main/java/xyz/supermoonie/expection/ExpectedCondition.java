package xyz.supermoonie.expection;

import xyz.supermoonie.controller.WebViewController;

/**
 * @author moonie
 * @date 2018/5/1
 */
public interface ExpectedCondition<T> {

    /**
     * 执行
     *
     * @param driver
     * @return
     */
    T apply(WebViewController driver);
}
