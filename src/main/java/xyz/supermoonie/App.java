package xyz.supermoonie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.command.ExecCommand;
import xyz.supermoonie.command.GetCookieCommand;
import xyz.supermoonie.command.LoadCommand;
import xyz.supermoonie.command.ScreenshotCommand;
import xyz.supermoonie.controller.WebViewDriver;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Base64;

/**
 * Hello world!
 *
 * @author Administrator
 */
public class App {
    public static void main(String[] args) {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
            String loadData = driver.sendCommand(loadCommand);
            System.out.println(loadData);

            String cookieData = driver.sendCommand(new GetCookieCommand());
            System.out.println(cookieData);
            ScreenshotCommand screenshotCommand = new ScreenshotCommand("img[src=VerifyImageServlet]");
            String captchaData = driver.sendCommand(screenshotCommand);
            System.out.println(captchaData);
            JSONObject captchaJson = JSON.parseObject(captchaData);
            String base64Image = captchaJson.getString("data");
            byte[] bytes = Base64.getDecoder().decode(base64Image);
            String account = JOptionPane.showInputDialog(null, "account");
            String password = JOptionPane.showInputDialog(null, "password");
            String captcha = (String) JOptionPane.showInputDialog(null, "captcha", "captcha", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(bytes), null, null);
            String js = String.format("$('input[name=username]').val('%s');" +
                    "$('input[name=password]').val('%s');" +
                    "$('input[name=imagecode]').val('%s');" +
                    "login_submit(loginform);", account, password, captcha);
            ExecCommand loginCommand = new ExecCommand(js);
            String loginData = driver.sendCommand(loginCommand);
            System.out.println(loginData);
        } catch (IOException e) {
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
