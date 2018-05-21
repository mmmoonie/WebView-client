package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

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
    private String[] extractors;
    private int count = 1;

    public ExtractCommand(String... extractors) {
        this.extractors = extractors;
    }

    public ExtractCommand(String[] extractors, int count) {
        this.extractors = extractors;
        this.count = count;
    }

    @Override
    public String generate() {
        if (null == extractors || extractors.length == 0) {
            throw new IllegalArgumentException("extractor is null");
        }
        JSONObject json = new JSONObject();
        json.put("op", "extract");
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(Arrays.asList(extractors));
        json.put("extractor", jsonArray);
        json.put("count", count);
        return json.toJSONString();
    }


}
