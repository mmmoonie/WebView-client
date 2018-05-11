package xyz.supermoonie.expection;

import com.alibaba.fastjson.JSONArray;
import xyz.supermoonie.command.ExtractCommand;
import xyz.supermoonie.command.GetCookieCommand;
import xyz.supermoonie.command.ProgressCommand;

import java.util.HashMap;
import java.util.Map;

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

    public static ExpectedCondition<Map<String, String>> extractFinished(String... extractors) {
        return driver -> {
            Map<String, String> dataMap = new HashMap<>(extractors.length);
            for (String extractor : extractors) {
                ExtractCommand extractCommand = new ExtractCommand(extractor);
                System.out.println(extractCommand.generate());
                String base64Data = driver.sendCommand(extractCommand);
                dataMap.put(extractor, base64Data);
            }
            return dataMap;
        };
    }

}
