package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.controller.WebViewDriver;
import org.junit.Test;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.parser.GetCookieParser;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.List;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class SetCookieCommandTest {

    @Test
    public void setCookie() throws Exception {
        try {
            WebViewDriver driver = null;
            List<HttpCookie> cookies;
            try {
                driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
                Wait wait = new Wait(driver);
                LoadCommand loadCommand = new LoadCommand(new URL("https://housing.ccb.com/tran/WCCMainPlatV5?CCB_IBSVersion=V5&isAjaxRequest=true&SERVLET_NAME=WCCMainPlatV5&TXCODE=NGJJ11&InsID=520109301001&Br_No=520000000"));
                wait.until(loadCommand, ExpectedConditions.loadFinished());
                cookies = driver.sendCommand(new GetCookieCommand(), new GetCookieParser());
                System.out.println(cookies);
                driver.sendCommand(new DeleteCookieCommand());
                wait.until(loadCommand, ExpectedConditions.loadFinished());
                List<HttpCookie> cookieList = driver.sendCommand(new GetCookieCommand(), new GetCookieParser());
                System.out.println(cookieList);
                driver.sendCommand(new DeleteCookieCommand());
                HttpCookie[] httpCookies = new HttpCookie[cookies.size() + 1];
                HttpCookie httpCookie = new HttpCookie("hello", "world");
                httpCookie.setDomain("housing.ccb.com");
                httpCookie.setPath("/");
                httpCookie.setMaxAge(0);
                httpCookie.setHttpOnly(false);
                httpCookie.setSecure(false);
                httpCookies[0] = httpCookie;
                for (int i = 1; i < cookies.size() + 1; i ++) {
                    httpCookies[i] = cookies.get(i - 1);
                }
                driver.sendCommand(new SetCookieCommand(httpCookies));
                cookieList = driver.sendCommand(new GetCookieCommand(), new GetCookieParser());
                System.out.println(cookieList);
            } finally {
                try {
                    if (driver != null) {
                        driver.close();
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