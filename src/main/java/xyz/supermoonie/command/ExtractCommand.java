package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * 截取命令，当 WebViewSpider 截取了数据时，可以使用此命令获取数据
 *
 * @author Administrator
 * @date 2018/5/11
 */
public class ExtractCommand extends AbstractCommand{

    /**
     * 截取器，作为从 Map 中获取的 key
     */
    private String extractor;

    public ExtractCommand(String extractor) {
        this.extractor = extractor;
    }

    @Override
    public String generate() {
        if (null == extractor) {
            throw new IllegalArgumentException("extractor is null");
        }
        JSONObject json = new JSONObject();
        json.put("op", "extract");
        json.put("extractor", extractor);
        return json.toJSONString();
    }

    public String getExtractor() {
        return extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }
}
