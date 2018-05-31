package xyz.supermoonie.command;

import xyz.supermoonie.controller.WebViewDriver;
import org.junit.Test;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

/**
 * @author moonie
 * @date 2018/4/30
 */
public class ProgressCommandTest {

    @Test
    public void progress() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            Wait wait = new Wait(driver);
            wait.until(new LoadCommand(new URL("https://persons.shgjj.com")), ExpectedConditions.loadFinished());

            Thread.sleep(1000);
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