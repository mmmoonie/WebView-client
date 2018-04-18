package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class LoadCommand extends AbstractCommand {

    public static final String IMAGE_INTERCEPTOR = ".*(png|gif|jpg).*";

    private String url;
    private String interceptor;

    public LoadCommand(String url) {
        this(url, "");
    }

    public LoadCommand(String url, String interceptor) {
        this.url = url;
        this.interceptor = interceptor;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "load");
        json.put("url", url);
        json.put("interceptor", interceptor);
        return json.toJSONString();
    }

    public String getInterceptor() {
        return interceptor;
    }

    public String getUrl() {
        return url;
    }
}
