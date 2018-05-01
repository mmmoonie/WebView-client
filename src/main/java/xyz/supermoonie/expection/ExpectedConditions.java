package xyz.supermoonie.expection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.command.ExecCommand;
import xyz.supermoonie.command.ProgressCommand;

import java.io.IOException;

/**
 * @author moonie
 * @date 2018/5/1
 */
public class ExpectedConditions {

    private static final String CODE_KEY = "code";
    private static final String DATA_KEY = "data";
    private static final int OK_CODE = 200;
    private static final int COMPLETE = 100;

    private ExpectedConditions() {
    }

    public static ExpectedCondition<Boolean> loadFinished() {
        ProgressCommand progressCommand = new ProgressCommand();
        return driver -> {
            try {
                String data = driver.sendCommand(progressCommand);
                System.out.println(data);
                JSONObject json = JSON.parseObject(data);
                if (OK_CODE == json.getIntValue(CODE_KEY)) {
                    if (COMPLETE == json.getIntValue(DATA_KEY)) {
                        return Boolean.TRUE;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } catch (IOException ignore) {
                return null;
            }
        };
    }

}
