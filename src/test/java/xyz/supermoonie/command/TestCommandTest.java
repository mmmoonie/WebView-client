package xyz.supermoonie.command;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * @author supermoonie
 * @date 2018/10/23 15:07
 */
public class TestCommandTest {

    @Test
    public void navigate() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
            TestCommand command = new TestCommand();
            command.setName("navigate");
            JSONObject parameters = new JSONObject();
            parameters.put("url", "http://httpbin.org/get");
            command.setParameters(parameters);
            driver.write(command);
            String loadData = driver.read();
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
    public void setProxy() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 9900));
            TestCommand command = new TestCommand();
            command.setName("setProxy");
            JSONObject parameters = new JSONObject();
            parameters.put("type", "HTTP");
            parameters.put("host", "140.143.85.200");
            parameters.put("port", 5998);
            command.setParameters(parameters);
            driver.write(command);
            String loadData = driver.read();
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
    public void setUserAgent() {
        WebViewDriver driver = null;
        try {
            driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 9900));
            TestCommand command = new TestCommand();
            command.setName("setUserAgent");
            JSONObject parameters = new JSONObject();
            parameters.put("userAgent", "Hello Qt!");
            command.setParameters(parameters);
            driver.write(command);
            String loadData = driver.read();
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

}