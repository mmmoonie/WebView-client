package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewDriver;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.parser.ScreenshotParser;
import xyz.supermoonie.wait.Wait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Random;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class ScreenshotCommandTest {

    @Test
    public void captcha() {
        for (int i = 0; i < 1; i ++) {
            WebViewDriver driver = null;
            try {
                driver = new WebViewDriver(new InetSocketAddress("127.0.0.1", 7100));
                Wait wait = new Wait(driver);

                wait.until(new LoadCommand(new URL("https://persons.shgjj.com")), ExpectedConditions.loadFinished());

                ScreenshotCommand screenshotCommand = new ScreenshotCommand("html");
                BufferedImage image = driver.sendCommand(screenshotCommand, new ScreenshotParser());
                ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + File.separator + "captcha.png"));

                Thread.sleep(1000);
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

    @Test
    public void test() throws InterruptedException {
        ScreenshotCommandTest test = new ScreenshotCommandTest();
        new Thread(test::captcha).start();
        new Thread(test::captcha).start();
        new Thread(test::captcha).start();
        new Thread(test::captcha).start();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void random() {
        Random random = new Random();
        random.ints(50, 0, 1000).forEach(System.out::println);

    }

    @Test
    public void dir() {
        System.out.println(System.getProperty("user.dir"));
    }

}