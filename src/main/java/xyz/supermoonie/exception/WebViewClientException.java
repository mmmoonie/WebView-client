package xyz.supermoonie.exception;

/**
 *
 * @author wangchao
 * @date 2018/5/7
 */
public class WebViewClientException extends RuntimeException{

    public WebViewClientException(String message) {
        super(message);
    }

    public WebViewClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
