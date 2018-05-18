package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * 加载页面的命令，WebViewSpider 接受的第一条命令必须为此命令
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class LoadCommand extends AbstractCommand {

    public static final Pattern IMAGE_INTERCEPTOR = Pattern.compile(".*(png|gif|jpg).*");

    /**
     * 要加载的 URL
     */
    private URL url;

    /**
     * 拦截器，此正则会匹配 URL 中的 path
     * 匹配到的话，此 URL 就不会发起网络请求，从而减少网络流量
     */
    private Pattern interceptor;

    /**
     * 截取器，此正则会匹配 URL 中的 path
     * 匹配到的话，此 URL 会被 WebViewSpider 缓存下到 Map 中，供 {@link ExtractCommand} 获取
     */
    private Pattern extractor;

    /**
     * 是否在load 之前清除 cookie
     */
    private Boolean clear = Boolean.FALSE;

    /**
     * 代理，设置了 proxy 后，WebViewSpider 将使用此代理访问网络
     */
    private Proxy proxy;

    public LoadCommand() {
    }

    public LoadCommand(URL url) {
        this(url, null, null, false, null);
    }

    public LoadCommand(URL url, Proxy proxy) {
        this(url, null, null, false, proxy);
    }

    public LoadCommand(URL url, Pattern interceptor) {
        this(url, interceptor, null, false, null);
    }

    public LoadCommand(URL url, Pattern interceptor, Pattern extractor) {
        this(url, interceptor, extractor, false, null);
    }

    public LoadCommand(URL url, Pattern interceptor, Pattern extractor, Boolean clear, Proxy proxy) {
        this.url = url;
        this.interceptor = interceptor;
        this.extractor = extractor;
        this.clear = clear;
        this.proxy = proxy;
    }

    @Override
    public String generate() {
        if (null == url) {
            throw new IllegalArgumentException("url is null");
        }
        JSONObject json = new JSONObject();
        json.put("op", "load");
        json.put("url", url.toString());
        if (null != interceptor) {
            json.put("interceptor", interceptor);
        }
        if (null != extractor) {
            json.put("extractor", extractor);
        }
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Pattern getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Pattern interceptor) {
        this.interceptor = interceptor;
    }

    public Pattern getExtractor() {
        return extractor;
    }

    public void setExtractor(Pattern extractor) {
        this.extractor = extractor;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Boolean getClear() {
        return clear;
    }

    public void setClear(Boolean clear) {
        this.clear = clear;
    }
}
