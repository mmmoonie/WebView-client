package xyz.supermoonie.expection;

import com.alibaba.fastjson.JSONArray;
import xyz.supermoonie.command.GetCookieCommand;
import xyz.supermoonie.command.ProgressCommand;

/**
 * @author moonie
 * @date 2018/5/1
 */
public class ExpectedConditions {

    private static final int COMPLETE = 100;

    private ExpectedConditions() {
    }

    public static ExpectedCondition<Boolean> loadFinished() {
        return driver -> {
            String progress = driver.sendCommand(new ProgressCommand());
            if (COMPLETE == Integer.parseInt(progress)) {
                return Boolean.TRUE;
            } else {
                return null;
            }
        };
    }

    public static ExpectedCondition<JSONArray> cookieLoaded(final String key) {
        return driver -> {
            String cookieData = driver.sendCommand(new GetCookieCommand());
            if (cookieData.contains(key)) {
                return JSONArray.parseArray(cookieData);
            }
            return null;
        };
    }

}
