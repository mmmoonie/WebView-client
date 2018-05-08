package xyz.supermoonie.expection;


import xyz.supermoonie.controller.WebViewDriver;

/**
 * @author moonie
 * @date 2018/5/1
 */
public interface ExpectedCondition<T> {

    /**
     * 执行
     *
     * @param driver
     * @throws Exception
     * @return
     */
    T apply(WebViewDriver driver) throws Exception;
}
