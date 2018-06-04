package xyz.supermoonie.command;

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
public class GetCookieCommandTest {

    @Test
    public void getCookie() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            Wait wait = new Wait(driver);

            wait.until(new LoadCommand(new URL("https://housing.ccb.com/tran/WCCMainPlatV5?CCB_IBSVersion=V5&isAjaxRequest=true&SERVLET_NAME=WCCMainPlatV5&TXCODE=NGJJ11&InsID=520109301001&Br_No=520000000")), ExpectedConditions.loadFinished());

            List<HttpCookie> cookieList = driver.sendCommand(new GetCookieCommand(), new GetCookieParser());
            System.out.println(cookieList);

            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (driver != null) {
                    driver.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}