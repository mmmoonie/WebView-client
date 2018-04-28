package xyz.supermoonie.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import xyz.supermoonie.controller.WebViewController;

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
            System.out.println(loadCommand.generate());
            String loadData = controller.sendCommand(loadCommand);
            System.out.println(loadData);

            ExecCommand loadImgCommand = new ExecCommand("" +
                    "var imgArr = document.querySelector('article').getElementsByTagName('img');" +
                    "for(var i = 0; i < imgArr.length; i ++) {" +
                    "var src = imgArr[i].dataset.src;" +
                    "var height = imgArr[i].dataset.hight;" +
                    "var width = imgArr[i].dataset.widht;" +
                    "imgArr[i].src = src;" +
                    "imgArr[i].width = width;" +
                    "imgArr[i].height = height;" +
                    "}");
            String loadImgData = controller.sendCommand(loadImgCommand);
            System.out.println(loadImgData);

            Thread.sleep(5000);
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