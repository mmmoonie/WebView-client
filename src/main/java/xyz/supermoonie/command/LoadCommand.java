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
     * 代理，设置了 proxy 后，WebViewSpider 将使用此代理访问网络
     */
    private Proxy proxy;

    public LoadCommand() {
    }

    public LoadCommand(URL url) {
        this(url, null, null);
    }

    public LoadCommand(URL url, Proxy proxy) {
        this(url, null, proxy);
    }

    public LoadCommand(URL url, Pattern interceptor) {
        this(url, interceptor, null);
    }


    public LoadCommand(URL url, Pattern interceptor, Proxy proxy) {
        this.url = url;
        this.interceptor = interceptor;
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

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
