package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author Administrator
 * @date 2018/5/11 0011
 */
public class ExtractCommand extends AbstractCommand{

    private String path;

    public ExtractCommand(String path) {
        this.path = path;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "extract");
        json.put("path", path);
        return json.toJSONString();
    }
}
