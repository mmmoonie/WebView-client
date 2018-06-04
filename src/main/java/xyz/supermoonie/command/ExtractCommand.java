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

    private int count;

    public ExtractCommand(String extractor) {
        this(extractor, 1);
    }

    public ExtractCommand(String extractor, Integer count) {
        this.extractor = extractor;
        this.count = count;
    }

    @Override
    public String generate() {
        if (null == extractor || "".equals(extractor)) {
            throw new IllegalArgumentException("extractor is empty");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("count less than zero");
        }
        JSONObject json = new JSONObject();
        json.put("op", "extract");
        json.put("extractor", extractor);
        json.put("count", count);
        return json.toJSONString();
    }


}
