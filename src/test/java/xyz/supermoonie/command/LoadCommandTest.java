package xyz.supermoonie.command;

import xyz.supermoonie.controller.WebViewDriver;
import org.junit.Test;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Base64;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class LoadCommandTest {

    @Test
    public void load() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand(new URL("https://persons.shgjj.com"));
            String loadData = driver.sendCommand(loadCommand);
            System.out.println(loadData);

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

    @Test
    public void loadWithProxy() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("192.168.1.161", 7100), 10000, 30000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("117.63.116.206", 5998));
            LoadCommand loadWithProxyCommand = new LoadCommand(new URL("https://ip.cn/"), proxy);
            System.out.println(loadWithProxyCommand.generate());
            String loadData = driver.sendCommand(loadWithProxyCommand);
            System.out.println(loadData);
            Thread.sleep(10000);
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

    static {
        System.setProperty("java.awt.headless", "false");
    }

    @Test
    public void loadLocalHtml() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("192.168.1.161", 7100), 10000, 30000);
            LoadCommand loadWithProxyCommand = new LoadCommand(new URL("file:///C:/html/ShenZhenGjjLogin.html"));
            System.out.println(loadWithProxyCommand.generate());
            driver.sendCommand(new AddExtractorCommand("/nbp/ranCode.jsp"));
            Wait wait = new Wait(driver);
            long start = System.currentTimeMillis();
            String base64Captcha = wait.until(loadWithProxyCommand, ExpectedConditions.extractFinished("/nbp/ranCode.jsp"));
            long diff = System.currentTimeMillis() - start;
            System.out.println("get captcha " + diff);
            byte[] bytes = Base64.getDecoder().decode(base64Captcha);
            String yzm = (String) JOptionPane.showInputDialog(null, "验证码", "请输入", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(bytes), null, null);
            System.out.println(yzm);
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