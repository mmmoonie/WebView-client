package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取 cookie 的命令
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class GetCookieCommand extends AbstractCommand {

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "getCookie");
        return json.toJSONString();
    }
}
