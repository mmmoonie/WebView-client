package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 *
 * @author Administrator
 * @date 2018/4/19 0019
 */
public class LoadWithProxyCommand extends LoadCommand {

    private Proxy proxy;

    public LoadWithProxyCommand(String url, Proxy proxy) {
        super(url);
        this.proxy = proxy;
    }

    public LoadWithProxyCommand(String url, String interceptor, Proxy proxy) {
        super(url, interceptor);
        this.proxy = proxy;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "loadWithProxy");
        json.put("url", super.getUrl());
        json.put("interceptor", super.getInterceptor());
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
        return json.toJSONString();
    }

    public Proxy getProxy() {
        return proxy;
    }
}
