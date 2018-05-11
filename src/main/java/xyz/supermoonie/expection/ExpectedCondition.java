package xyz.supermoonie.expection;


import xyz.supermoonie.controller.WebViewDriver;

/**
 * 执行命令以期望得到某种结果
 *
 * @author moonie
 * @date 2018/5/1
 */
public interface ExpectedCondition<T> {

    /**
     * 执行
     *
     * @param driver        WebViewSpider 驱动
     * @throws Exception    异常
     * @return              结果
     */
    T apply(WebViewDriver driver) throws Exception;
}
