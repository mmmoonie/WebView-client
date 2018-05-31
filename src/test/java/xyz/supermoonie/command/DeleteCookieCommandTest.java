package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DeleteCookieCommandTest {

    @Test
    public void deleteCookie() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));

            DeleteCookieCommand deleteCookieCommand = new DeleteCookieCommand();
            String data = driver.sendCommand(deleteCookieCommand);
            System.out.println(data);

            GetCookieCommand getCookieCommand = new GetCookieCommand();
            data = driver.sendCommand(getCookieCommand);
            System.out.println(data);

            Thread.sleep(1500);
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