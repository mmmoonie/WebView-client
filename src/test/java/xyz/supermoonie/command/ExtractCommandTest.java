package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.loop.Loop;
import xyz.supermoonie.wait.Wait;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Base64;
import java.util.Map;
import java.util.regex.Pattern;

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
            Wait wait = new Wait(driver, 10000, 500);

            String base64Data = wait.until(new LoadCommand(new URL("https://persons.shgjj.com")), ExpectedConditions.extractFinished("/VerifyImageServlet"));

            byte[] bytes = Base64.getDecoder().decode(base64Data);
            JOptionPane.showMessageDialog(null, new ImageIcon(bytes), "captcha", JOptionPane.INFORMATION_MESSAGE);

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