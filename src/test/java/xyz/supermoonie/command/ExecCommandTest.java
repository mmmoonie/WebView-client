package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.loop.Loop;
import xyz.supermoonie.wait.Wait;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Base64;
import java.util.Map;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class ExecCommandTest {

    @Test
    public void exec() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com/");
            loadCommand.setExtractor("/VerifyImageServlet");
            Wait wait = new Wait(driver);
            Loop loop = new Loop(driver, wait);
            Map<String, String> dataMap = loop.begin(loadCommand, ExpectedConditions.extractFinished("/VerifyImageServlet"));
            String base64Img = dataMap.get("/VerifyImageServlet");
            byte[] bytes = Base64.getDecoder().decode(base64Img);
            String account = JOptionPane.showInputDialog(null, "account");
            String password = JOptionPane.showInputDialog(null, "password");
            String captcha = (String) JOptionPane.showInputDialog(null, "captcha", "captcha", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(bytes), null, null);
            String js = String.format("$('input[name=username]').val('%s');" +
                    "$('input[name=password]').val('%s');" +
                    "$('input[name=imagecode]').val('%s');" +
                    "login_submit(loginform);", account, password, captcha);

            ExecCommand execCommand = new ExecCommand(js, "/MainServlet");
            driver.sendCommand(execCommand);
            String html = wait.until(ExpectedConditions.extractFinished("/MainServlet")).get("/MainServlet");
            System.out.println(html);

            Thread.sleep(15000);
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