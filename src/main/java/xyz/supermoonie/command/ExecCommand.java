package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author wangchao
 * @date 2018/4/19
 */
public class ExecCommand extends AbstractCommand {

    private String javaScript;

    private String extractor;

    public ExecCommand(String javaScript) {
        this(javaScript, "");
    }

    public ExecCommand(String javaScript, String extractor) {
        this.javaScript = javaScript;
        this.extractor = extractor;
    }

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "exec");
        json.put("js", javaScript);
        json.put("extractor", extractor);
        return json.toJSONString();
    }

    public String getJavaScript() {
        return javaScript;
    }

    public void setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }

    public String getExtractor() {
        return extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }
}
