package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Base64;

import static org.junit.Assert.*;

public class AddExtractorCommandTest {

    @Test
    public void addExtractor() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            driver.sendCommand(new AddExtractorCommand("/ValidateCode.aspx"));
            Wait wait = new Wait(driver, 10000, 500);

            String base64Data = wait.until(new LoadCommand(new URL("http://61.136.223.44/web2/src/index/login.html")), ExpectedConditions.extractFinished("/ValidateCode.aspx"));

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