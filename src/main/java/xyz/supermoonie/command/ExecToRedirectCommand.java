package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class ExecToRedirectCommand extends ExecCommand {

    public ExecToRedirectCommand(String javaScript) {
        super(javaScript);
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "execToRedirect");
        json.put("js", getJavaScript());
        return json.toJSONString();
    }


}
