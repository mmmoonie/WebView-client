package xyz.supermoonie.expection;

import com.alibaba.fastjson.JSONArray;
import xyz.supermoonie.command.ExtractCommand;
import xyz.supermoonie.command.GetAllCookieCommand;
import xyz.supermoonie.command.ProgressCommand;

import java.util.ArrayList;
import java.util.List;

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
            String cookieData = driver.sendCommand(new GetAllCookieCommand());
            if (cookieData.contains(key)) {
                return JSONArray.parseArray(cookieData);
            }
            return null;
        };
    }

    public static ExpectedCondition<List<String>> extractFinished(String extractor, int count) {
        return driver -> {
            ExtractCommand extractCommand = new ExtractCommand(extractor, count);
            String dataArrayText = driver.sendCommand(extractCommand);
            JSONArray dataArray = JSONArray.parseArray(dataArrayText);
            if (dataArray.size() == 0) {
                return null;
            }
            List<String> dataList = new ArrayList<>(dataArray.size());
            for (int i = 0; i < dataArray.size(); i ++) {
                String value = dataArray.getString(i);
                dataList.add(value);
            }
            return dataList;
        };
    }

    public static ExpectedCondition<String> extractFinished(String extractor) {
        return driver -> {
            ExtractCommand extractCommand = new ExtractCommand(extractor, 1);
            String dataArrayText = driver.sendCommand(extractCommand);
            JSONArray dataArray = JSONArray.parseArray(dataArrayText);
            if (dataArray.size() == 0) {
                return null;
            }
            return dataArray.getString(0);
        };
    }

}
