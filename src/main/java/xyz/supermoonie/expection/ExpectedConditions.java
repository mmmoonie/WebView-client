package xyz.supermoonie.expection;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.command.ExtractCommand;
import xyz.supermoonie.command.GetCookieCommand;
import xyz.supermoonie.command.ProgressCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用的期望的条件的集合
 *
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
            ExtractCommand extractCommand = new ExtractCommand(extractors);
            System.out.println(extractCommand.generate());
            String dataArrayText = driver.sendCommand(extractCommand);
            JSONArray dataArray = JSONArray.parseArray(dataArrayText);
            Map<String, String> dataMap = new HashMap<>(dataArray.size());
            for (int i = 0; i < dataArray.size(); i ++) {
                JSONObject json = dataArray.getJSONObject(i);
                String key = json.keySet().iterator().next();
                String base64Value = json.getString(key);
                dataMap.put(key, base64Value);
            }
            return dataMap;
        };
    }

}
