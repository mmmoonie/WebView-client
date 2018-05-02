package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewController;
import xyz.supermoonie.expection.ExpectedConditions;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * Created by Administrator on 2018/4/27 0027.
 */
public class PrintPdfCommandTest {

    @Test
    public void printPdf() {
        WebViewController controller = null;
        try {
            controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://juejin.im/entry/5ae2c177f265da0b722ad90b");
            String loadData = controller.sendCommand(loadCommand);
            System.out.println(loadData);

            Wait wait = new Wait(controller, 20000, 300);
            wait.until(ExpectedConditions.loadFinished());

            ExecCommand loadImgCommand = new ExecCommand("" +
                    "var imgArr = document.querySelector('article').getElementsByTagName('img');" +
                    "for(var i = 0; i < imgArr.length; i ++) {" +
                    "var src = imgArr[i].dataset.src;" +
                    "var height = imgArr[i].dataset.height;" +
                    "var width = imgArr[i].dataset.width;" +
                    "imgArr[i].src = src;" +
                    "imgArr[i].width = width;" +
                    "imgArr[i].height = height;" +
                    "}");
            String loadImgData = controller.sendCommand(loadImgCommand);
            System.out.println(loadImgData);

            wait.until(ExpectedConditions.loadFinished());

            PrintPdfCommand printPdfCommand = new PrintPdfCommand();
            String printPdfData = controller.sendCommand(printPdfCommand);
            System.out.println(printPdfData);

            Thread.sleep(5000);
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