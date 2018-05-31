package xyz.supermoonie.parser;

import java.util.Base64;

/**
 *
 * @author Administrator
 * @date 2018/6/1 0001
 */
public class ExtractReturnStringParser implements Parser<String> {

    private String charset;

    public ExtractReturnStringParser(String charset) {
        this.charset = charset;
    }

    public ExtractReturnStringParser() {
        this("UTF-8");
    }

    @Override
    public String parse(String result) throws Exception {
        return new String(Base64.getDecoder().decode(result), charset);
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
