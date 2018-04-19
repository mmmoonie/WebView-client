package xyz.supermoonie.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import xyz.supermoonie.controller.WebViewController;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.InetSocketAddress;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class SetCookieCommandTest {

    @Test
    public void setCookie() {
        try {
            WebViewController controller = null;
            HttpCookie[] cookies = null;
            try {
                controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100));
                LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
                String loadData = controller.sendCommand(loadCommand);
                System.out.println(loadData);

                String cookieData = controller.sendCommand(new GetCookieCommand());
                System.out.println(cookieData);
                JSONObject cookiesJson = JSON.parseObject(cookieData);
                JSONArray cookieArray = cookiesJson.getJSONArray("data");
                cookies = new HttpCookie[cookieArray.size()];
                for (int i = 0; i < cookieArray.size(); i ++) {
                    JSONObject cookieJson = cookieArray.getJSONObject(i);
                    String name = cookieJson.getString("name");
                    String value = cookieJson.getString("value");
                    HttpCookie cookie = new HttpCookie(name, value);
                    cookies[i] = cookie;
                }
            } finally {
                try {
                    if (controller != null) {
                        controller.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            WebViewController webViewController = null;
            try {
                webViewController = new WebViewController(new InetSocketAddress("127.0.0.1", 7100));
                LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
                String loadData = webViewController.sendCommand(loadCommand);
                System.out.println(loadData);

                SetCookieCommand setCookieCommand = new SetCookieCommand(cookies);
                String setCookieData = webViewController.sendCommand(setCookieCommand);
                System.out.println(setCookieData);

                String cookieData = webViewController.sendCommand(new GetCookieCommand());
                System.out.println(cookieData);

            } finally {
                try {
                    if (webViewController != null) {
                        webViewController.close();
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