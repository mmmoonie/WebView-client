package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wangchao
 * @date 2018/05/31
 */
public class DeleteCookieCommand extends AbstractCommand {

    private String url = "";

    public DeleteCookieCommand() {
    }

    public DeleteCookieCommand(String url) {
        this.url = url;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "deleteCookie");
        json.put("url", url);
        return json.toJSONString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
