package xyz.supermoonie.command;

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
//            Thread.sleep(5000);

            ExecCommand hrefCommand = new ExecCommand("location.href");
            String href = controller.sendCommand(hrefCommand);
            System.out.println(href);

            PrintPdfCommand printPdfCommand = new PrintPdfCommand();
            String printPdfData = controller.sendCommand(printPdfCommand);
            System.out.println(printPdfData);

            Thread.sleep(1000);
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