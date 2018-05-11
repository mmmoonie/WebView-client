package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * 截图命令
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class ScreenshotCommand extends AbstractCommand {

    /**
     * dom 选择器，被此选择器选中的 dom ，
     * WebViewSpider 将会对其进行截图，并将截图进行 base64 编码发送回来
     */
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

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
