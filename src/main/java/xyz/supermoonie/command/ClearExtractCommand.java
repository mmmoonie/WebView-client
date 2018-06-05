package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wangchao
 */
public class ClearExtractCommand extends AbstractCommand {
    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "clearExtract");
        return json.toJSONString();
    }
}
