package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * @author supermoonie
 * @date 2018/10/23 15:03
 */
public class TestCommand extends AbstractCommand{

    private String name;

    private JSONObject parameters;

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("parameters", parameters);
        return json.toJSONString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getParameters() {
        return parameters;
    }

    public void setParameters(JSONObject parameters) {
        this.parameters = parameters;
    }
}
