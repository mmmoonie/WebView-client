package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

import java.util.regex.Pattern;

/**
 * 执行 javaScript 代码命令，javaScript 代码分为两类：
 *      1、 无返回结果，此类 javaScript 代码主要是做填写表单，执行登录操作等。此时应设置 extractor 进行页面跳转的截取
 *      2、 有返回结果，此类 javaScript 代码主要是做页面获取，javaScript 加密的结果获取等。此时无需设置 extractor 。
 *
 * @author wangchao
 * @date 2018/4/19
 */
public class ExecCommand extends AbstractCommand {

    /**
     * 需要执行的 javaScript 代码
     */
    private String javaScript;

    /**
     * 截取器，此正则会匹配 URL 中的 path ，
     * 如果匹配的话，会被 WebViewSpider 缓存下到 Map 中，供 {@link ExtractCommand} 获取
     * 其中，URL 中的 path 会用作 Map 中的 key
     */
    private Pattern extractor;

    public ExecCommand(String javaScript) {
        this(javaScript, null);
    }

    public ExecCommand(String javaScript, Pattern extractor) {
        this.javaScript = javaScript;
        this.extractor = extractor;
    }

    @Override
    public String generate() {
        if (null == javaScript || javaScript.trim().length() == 0) {
            throw new IllegalArgumentException("javaScript is null or blank");
        }
        JSONObject json = new JSONObject();
        json.put("op", "exec");
        json.put("js", javaScript);
        if (null != extractor) {
            json.put("extractor", extractor.toString());
        }
        return json.toJSONString();
    }

    public String getJavaScript() {
        return javaScript;
    }

    public void setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }

    public Pattern getExtractor() {
        return extractor;
    }

    public void setExtractor(Pattern extractor) {
        this.extractor = extractor;
    }
}
