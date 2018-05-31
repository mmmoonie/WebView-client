package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.HttpCookie;

/**
 * 设置 cookie 的命令
 * 此命令在设置 cookie 的时候，网页中必须已经含有 HttpCookie[] 中的 cookie，才能设置成功
 *
 * @author wangchao
 * @date 2018/4/19
 */
public class SetCookieCommand extends AbstractCommand {

    /**
     * cookie 列表
     */
    private HttpCookie[] cookies;

    public SetCookieCommand(HttpCookie[] cookies) {
        this.cookies = cookies.clone();
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "setCookie");
        JSONArray cookieArray = new JSONArray();
        for (HttpCookie cookie: cookies) {
            JSONObject cookieJson = new JSONObject();
            cookieJson.put("name", cookie.getName());
            cookieJson.put("value", cookie.getValue());
            cookieJson.put("domain", cookie.getDomain());
            cookieJson.put("path", cookie.getPath());
            cookieJson.put("expirationDate", cookie.getMaxAge());
            cookieJson.put("httpOnly", cookie.isHttpOnly());
            cookieJson.put("secure", cookie.getSecure());
            cookieArray.add(cookieJson);
        }
        json.put("cookies", cookieArray);
        return json.toJSONString();
    }

}
