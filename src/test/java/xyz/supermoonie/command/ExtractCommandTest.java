package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * Created by Administrator on 2018/5/11 0011.
 */
public class ExtractCommandTest {

    @Test
    public void extract() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
            loadCommand.setExtractor("/VerifyImageServlet");
            driver.sendCommand(loadCommand);
            Wait wait = new Wait(driver);
            wait.until(ExpectedConditions.loadFinished());

            ExtractCommand extractCommand = new ExtractCommand("/VerifyImageServlet");
            String extractData = driver.sendCommand(extractCommand);
            System.out.println(extractData);

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