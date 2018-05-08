package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;

import java.io.IOException;
import java.net.InetSocketAddress;

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

            LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
            String loadData = driver.sendCommand(loadCommand);
            System.out.println(loadData);

            GetCookieCommand getCookieCommand = new GetCookieCommand();
            String cookieData = driver.sendCommand(getCookieCommand);
            System.out.println(cookieData);

            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
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