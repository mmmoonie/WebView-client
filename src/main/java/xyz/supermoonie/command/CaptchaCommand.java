package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class CaptchaCommand extends AbstractCommand {

    private String selector;

    public CaptchaCommand(String selector) {
        this.selector = selector;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "captcha");
        json.put("selector", selector);
        return json.toJSONString();
    }

    public String getSelector() {
        return selector;
    }
}
