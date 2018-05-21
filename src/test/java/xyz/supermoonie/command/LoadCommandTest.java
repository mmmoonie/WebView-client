package xyz.supermoonie.command;

import xyz.supermoonie.controller.WebViewDriver;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

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
            LoadCommand loadCommand = new LoadCommand(new URL("https://www.baidu.com"));
            System.out.println(loadCommand.generate());
            String loadData = driver.sendCommand(loadCommand);
            System.out.println(loadData);
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
    public void loadWithProxy() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100), 10000, 30000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("47.104.63.239", 5998));
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

}