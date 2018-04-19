package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author wangchao
 * @date 2018/4/19
 */
public class ExecCommand extends AbstractCommand {

    private String javaScript;

    public ExecCommand(String javaScript) {
        this.javaScript = javaScript;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "exec");
        json.put("js", javaScript);
        return json.toJSONString();
    }

    public String getJavaScript() {
        return javaScript;
    }
}
