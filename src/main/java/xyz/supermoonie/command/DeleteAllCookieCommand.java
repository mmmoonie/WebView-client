package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wangchao
 * @date 2018/05/31
 */
public class DeleteAllCookieCommand extends AbstractCommand {

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "deleteAllCookie");
        return json.toJSONString();
    }
}
