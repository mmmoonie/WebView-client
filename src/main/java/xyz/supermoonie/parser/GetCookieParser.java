package xyz.supermoonie.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 * @date 2018/5/31 0031
 */
public class GetCookieParser implements Parser<List<HttpCookie>> {

    @Override
    public List<HttpCookie> parse(String result) throws Exception{
        if (null == result || "".equals(result)) {
            return null;
        }
        JSONArray cookieArray = JSONArray.parseArray(result);
        List<HttpCookie> cookieList = new ArrayList<>(cookieArray.size());
        for (int i = 0; i < cookieArray.size(); i ++) {
            JSONObject cookieJson = cookieArray.getJSONObject(i);
            String name = cookieJson.getString("name");
            String value = cookieJson.getString("value");
            String domain = cookieJson.getString("domain");
            String path = cookieJson.getString("path");
            long expirationDate = cookieJson.getLong("expirationDate");
            boolean httpOnly = cookieJson.getBooleanValue("httpOnly");
            boolean secure = cookieJson.getBooleanValue("secure");
            HttpCookie httpCookie = new HttpCookie(name, value);
            httpCookie.setDomain(domain);
            httpCookie.setPath(path);
            httpCookie.setMaxAge(expirationDate);
            httpCookie.setHttpOnly(httpOnly);
            httpCookie.setSecure(secure);
            cookieList.add(httpCookie);
        }
        return cookieList;
    }
}
