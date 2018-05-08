package xyz.supermoonie.exception;

/**
 *
 * @author wangchao
 * @date 2018/5/8
 */
public class WebViewConnectException extends WebViewClientException {

    public WebViewConnectException(String message) {
        super(message);
    }

    public WebViewConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
