package xyz.supermoonie.loop;

import xyz.supermoonie.command.LoadCommand;
import xyz.supermoonie.controller.WebViewDriver;
import org.junit.Test;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

/**
 *
 * Created by wangchao on 2018/5/7.
 */
public class LoopTest {
    @Test
    public void begin() throws Exception {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand(new URL("https://persons.shgjj.com"));

            Boolean loaded = new Loop(new Wait(driver)).begin(loadCommand, ExpectedConditions.loadFinished());
            System.out.println(loaded);

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