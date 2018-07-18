package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wangchao
 */
public class AddExtractorCommand extends AbstractCommand {

    private String extractor;

    public AddExtractorCommand(String extractor) {
        this.extractor = extractor;
    }

    @Override
    public String generate() {
        if (null == extractor || "".equals(extractor)) {
            throw new IllegalArgumentException("extractor is empty");
        }
        JSONObject json = new JSONObject();
        json.put("op", "addExtractor");
        json.put("extractor", extractor);
        return json.toJSONString();
    }
}
