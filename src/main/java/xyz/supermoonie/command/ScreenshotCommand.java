package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class ScreenshotCommand extends AbstractCommand {

    private String selector;

    public ScreenshotCommand(String selector) {
        this.selector = selector;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "screenshot");
        json.put("selector", selector);
        return json.toJSONString();
    }

    public String getSelector() {
        return selector;
    }
}
