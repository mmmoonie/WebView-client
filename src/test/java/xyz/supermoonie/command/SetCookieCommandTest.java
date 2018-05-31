package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.controller.WebViewDriver;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.URL;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class SetCookieCommandTest {

    @Test
    public void setCookie() {
        try {
            WebViewDriver driver = null;
            HttpCookie[] cookies = null;
//            try {
//                driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
//                LoadCommand loadCommand = new LoadCommand(new URL("https://persons.shgjj.com"));
//                String loadData = driver.sendCommand(loadCommand);
//                System.out.println(loadData);
//
//                String cookieData = driver.sendCommand(new GetCookieCommand());
//                JSONArray cookieArray = JSONArray.parseArray(cookieData);
//                cookies = new HttpCookie[cookieArray.size()];
//                for (int i = 0; i < cookieArray.size(); i ++) {
//                    JSONObject cookieJson = cookieArray.getJSONObject(i);
//                    String name = cookieJson.getString("name");
//                    String value = cookieJson.getString("value");
//                    HttpCookie cookie = new HttpCookie(name, value);
//                    cookies[i] = cookie;
//                }
//            } finally {
//                try {
//                    if (driver != null) {
//                        driver.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            WebViewDriver webViewDriver = null;
            try {
                webViewDriver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
//                LoadCommand loadCommand = new LoadCommand(new URL("https://persons.shgjj.com"));
//                String loadData = webViewDriver.sendCommand(loadCommand);
//                System.out.println(loadData);
                cookies = new HttpCookie[1];
                HttpCookie cookie = new HttpCookie("hello", "world");
                cookie.setDomain("persons.shgjj.com");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setHttpOnly(false);
                cookie.setSecure(false);
                cookies[0] = cookie;
                SetCookieCommand setCookieCommand = new SetCookieCommand(cookies);
                String setCookieData = webViewDriver.sendCommand(setCookieCommand);
                System.out.println(setCookieData);

                String cookieData = webViewDriver.sendCommand(new GetCookieCommand());
                System.out.println(cookieData);

            } finally {
                try {
                    if (webViewDriver != null) {
                        webViewDriver.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}