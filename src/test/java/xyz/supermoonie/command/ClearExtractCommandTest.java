package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

public class ClearExtractCommandTest {

    @Test
    public void clearExtract() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            Wait wait = new Wait(driver);

            wait.until(new LoadCommand(new URL("https://persons.shgjj.com")), ExpectedConditions.loadFinished());

            driver.sendCommand(new ClearExtractCommand());

            String data = driver.sendCommand(new ExtractCommand("/VerifyImageServlet"));

            System.out.println(data);

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