package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.HttpCookie;

/**
 *
 * @author wangchao
 * @date 2018/4/19
 */
public class SetCookieCommand extends AbstractCommand {

    private HttpCookie[] cookies;

    public SetCookieCommand(HttpCookie[] cookies) {
        this.cookies = cookies;
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
            cookieArray.add(cookieJson);
        }
        json.put("cookies", cookieArray);
        return json.toJSONString();
    }

    public HttpCookie[] getCookies() {
        return cookies;
    }
}
