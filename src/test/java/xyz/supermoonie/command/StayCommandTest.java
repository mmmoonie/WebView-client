package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

/**
 * @author supermoonie
 * @date 2018/12/29
 */
public class StayCommandTest {

    @Test
    public void stay() {
        String id = StayCommand.generateId();
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand(new URL("https://persons.shgjj.com"), LoadCommand.IMAGE_INTERCEPTOR);
            driver.write(loadCommand);
            StayCommand stayCommand = new StayCommand(id, 30_000);
            String s = driver.sendCommand(stayCommand);
            System.out.println(s);
            Thread.sleep(5000);
        } catch (Exception e) {
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
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            ExistCommand existCommand = new ExistCommand(id);
            String s = driver.sendCommand(existCommand);
            System.out.println(s);
            LoadCommand loadCommand = new LoadCommand(new URL("https://www.baidu.com"), LoadCommand.IMAGE_INTERCEPTOR);
            driver.write(loadCommand);
            Thread.sleep(5000);
        } catch (Exception e) {
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