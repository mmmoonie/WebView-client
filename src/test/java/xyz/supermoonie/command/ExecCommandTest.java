package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;

import java.io.IOException;
import java.net.InetSocketAddress;

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
            System.out.println(loadCommand.generate());
            String loadData = driver.sendCommand(loadCommand);
            System.out.println(loadData);

            ExecCommand execCommand = new ExecCommand("document.getElementsByTagName('html')[0].innerHTML.replace(/\\s/g, ' ')");
            String execData = driver.sendCommand(execCommand);
            System.out.println(execData);

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