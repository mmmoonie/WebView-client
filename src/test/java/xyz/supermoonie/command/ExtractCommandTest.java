package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.loop.Loop;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

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
            loadCommand.setExtractor("(/VerifyImageServlet|/js/md5.js)");
            driver.sendCommand(loadCommand);
            Wait wait = new Wait(driver, 20000, 500);
            Loop loop = new Loop(driver, wait);
            Map<String, String> dataMap = loop.begin(loadCommand, ExpectedConditions.extractFinished("/js/md5.js", "/VerifyImageServlet"));
            System.out.println(dataMap.get("/VerifyImageServlet"));
            System.out.println("\n\n");
            System.out.println(dataMap.get("/js/md5.js"));

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