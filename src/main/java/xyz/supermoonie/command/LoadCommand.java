package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class LoadCommand extends AbstractCommand {

    public static final String IMAGE_INTERCEPTOR = ".*(png|gif|jpg).*";

    private String url;
    private String interceptor;
    private String extractor;
    private Proxy proxy;

    public LoadCommand() {
    }

    public LoadCommand(String url) {
        this(url, "", "", null);
    }

    public LoadCommand(String url, Proxy proxy) {
        this(url, "", "", proxy);
    }

    public LoadCommand(String url, String interceptor) {
        this(url, interceptor, "", null);
    }

    public LoadCommand(String url, String interceptor, String extractor) {
        this(url, interceptor, extractor, null);
    }

    public LoadCommand(String url, String interceptor, String extractor, Proxy proxy) {
        this.url = url;
        this.interceptor = interceptor;
        this.extractor = extractor;
        this.proxy = proxy;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "load");
        json.put("url", url);
        json.put("interceptor", interceptor == null ? "" : interceptor);
        json.put("extractor", extractor == null ? "" : extractor);
        if (proxy != null) {
            JSONObject proxyJson = new JSONObject();
            if (proxy.type() == Proxy.Type.SOCKS) {
                proxyJson.put("type", "socks");
            } else {
                proxyJson.put("type", "http");
            }
            InetSocketAddress address = (InetSocketAddress) proxy.address();
            proxyJson.put("ip", address.getHostString());
            proxyJson.put("port", address.getPort());
            json.put("proxy", proxyJson);
        }
        return json.toJSONString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(String interceptor) {
        this.interceptor = interceptor;
    }

    public String getExtractor() {
        return extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
