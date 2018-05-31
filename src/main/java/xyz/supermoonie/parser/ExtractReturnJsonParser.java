package xyz.supermoonie.parser;

import com.alibaba.fastjson.JSONObject;

import java.util.Base64;

/**
 *
 * @author Administrator
 * @date 2018/6/1 0001
 */
public class ExtractReturnJsonParser implements Parser<JSONObject> {

    private String charset;

    public ExtractReturnJsonParser(String charset) {
        this.charset = charset;
    }

    public ExtractReturnJsonParser() {
        this("UTF-8");
    }

    @Override
    public JSONObject parse(String result) throws Exception {
        String text = new String(Base64.getDecoder().decode(result), charset);
        return JSONObject.parseObject(text);
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
