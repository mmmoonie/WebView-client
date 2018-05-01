package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
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
