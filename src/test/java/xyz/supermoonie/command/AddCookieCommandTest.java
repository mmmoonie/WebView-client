package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
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
public class AddCookieCommandTest {

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
                cookies = driver.sendCommand(new GetAllCookieCommand(), new GetCookieParser());
                System.out.println(cookies);
                driver.sendCommand(new DeleteAllCookieCommand());
                wait.until(loadCommand, ExpectedConditions.loadFinished());
                List<HttpCookie> cookieList = driver.sendCommand(new GetAllCookieCommand(), new GetCookieParser());
                System.out.println(cookieList);
                driver.sendCommand(new DeleteAllCookieCommand());
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
                driver.sendCommand(new AddCookieCommand(httpCookies));
                cookieList = driver.sendCommand(new GetAllCookieCommand(), new GetCookieParser());
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

    @Test
    public void addCookie() throws Exception {
        try {
            WebViewDriver driver = null;
            try {
                driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
                Wait wait = new Wait(driver);
                wait.until(new LoadCommand(new URL("https://puser.zjzwfw.gov.cn/sso/usp.do?action=ssoLogin&servicecode=jhsgjjcx")), ExpectedConditions.loadFinished());
                List<HttpCookie> httpCookieList = driver.sendCommand(new GetAllCookieCommand(), new GetCookieParser());
                System.out.println(JSONObject.toJSONString(httpCookieList, true));
                HttpCookie httpCookie = new HttpCookie("hello", "word");
                httpCookieList.add(httpCookie);
                driver.sendCommand(new AddCookieCommand(httpCookieList.toArray(new HttpCookie[0])));
                List<HttpCookie> httpCookies = driver.sendCommand(new GetAllCookieCommand(), new GetCookieParser());
                System.out.println(JSONObject.toJSONString(httpCookies, true));
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