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
 * Created by Administrator on 2018/5/11 0011.
 */
public class ExtractCommandTest {

    @Test
    public void extract() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand(new URL("https://persons.shgjj.com"));
            Wait wait = new Wait(driver, 10000, 500);
            Loop loop = new Loop(driver, wait);
            Map<String, String> dataMap = loop.begin(loadCommand, ExpectedConditions.extractFinished("https://persons.shgjj.com/VerifyImageServlet"));
            String base64Data = dataMap.get("https://persons.shgjj.com/VerifyImageServlet");
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

    @Test
    public void extractWithCount() throws Exception {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand(new URL("https://housing.ccb.com/tran/WCCMainPlatV5?CCB_IBSVersion=V5&isAjaxRequest=true&SERVLET_NAME=WCCMainPlatV5&TXCODE=NGJJ11&InsID=520109301001&Br_No=520000000"));
            Wait wait = new Wait(driver, 10000, 500);
            Loop loop = new Loop(driver, wait);
            Map<String, String> dataMap = loop.begin(loadCommand, ExpectedConditions.extractFinished("/NCCB_Encoder/Encoder"));
            System.out.println(dataMap.get("/NCCB_Encoder/Encoder"));

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