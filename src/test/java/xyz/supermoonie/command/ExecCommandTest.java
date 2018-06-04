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
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
            Wait wait = new Wait(driver);
            String base64Img = wait.until(new LoadCommand(new URL("https://persons.shgjj.com/")), ExpectedConditions.extractFinished("/VerifyImageServlet"));
            byte[] bytes = Base64.getDecoder().decode(base64Img);

            String account = JOptionPane.showInputDialog(null, "account");
            String password = JOptionPane.showInputDialog(null, "password");
            String captcha = (String) JOptionPane.showInputDialog(null, "captcha", "captcha", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(bytes), null, null);
            String js = String.format("$('input[name=username]').val('%s');" +
                    "$('input[name=password]').val('%s');" +
                    "$('input[name=imagecode]').val('%s');" +
                    "login_submit(loginform);", account, password, captcha);

            String mainServlet = Pattern.compile("/MainServlet\\?username=.+&password=.+&imagecode=.+&password_md5=.+&ID=0").toString();
            String result = wait.until(new ExecCommand(js), ExpectedConditions.extractFinished(mainServlet));
            System.out.println(new String(Base64.getDecoder().decode(result), "GBK"));

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