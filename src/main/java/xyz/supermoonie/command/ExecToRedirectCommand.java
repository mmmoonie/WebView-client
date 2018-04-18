package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class ExecToRedirectCommand extends AbstractCommand {

    private String javaScript;

    public ExecToRedirectCommand(String javaScript) {
        this.javaScript = javaScript;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "execToRedirect");
        json.put("js", javaScript);
        return json.toJSONString();
    }

    public String getJavaScript() {
        return javaScript;
    }
}
