package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取加载进度的指令
 * 网页加载进度为 0-100
 *
 * @author moonie
 * @date 2018/4/30
 */
public class ProgressCommand extends AbstractCommand {

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "progress");
        return json.toJSONString();
    }
}
