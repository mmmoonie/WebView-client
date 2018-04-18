package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 *
 * Created by Administrator on 2018/4/19 0019.
 */
public class LoadWithProxyCommandTest {

    @Test
    public void loadWithProxy() {
        WebViewController controller = null;
        try {
            controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100), 10000, 30000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("221.7.255.168", 8080));
            LoadWithProxyCommand loadWithProxyCommand = new LoadWithProxyCommand("http://httpbin.org/ip", proxy);
            System.out.println(loadWithProxyCommand.generate());
            String loadData = controller.sendCommand(loadWithProxyCommand);
            System.out.println(loadData);
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (controller != null) {
                    controller.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}