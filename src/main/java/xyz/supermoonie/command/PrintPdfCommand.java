package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;

/**
 * 打印 PDF 命令
 *
 * @author Administrator
 * @date 2018/4/27 0027
 */
public class PrintPdfCommand extends AbstractCommand {

    @Override
    public String generate() {
        JSONObject json = new JSONObject();
        json.put("op", "printPdf");
        return json.toJSONString();
    }
}
