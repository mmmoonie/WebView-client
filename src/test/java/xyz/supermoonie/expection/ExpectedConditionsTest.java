package xyz.supermoonie.expection;

import org.junit.Test;
import xyz.supermoonie.command.LoadCommand;
import xyz.supermoonie.controller.WebViewController;
import xyz.supermoonie.wait.Wait;

import java.io.IOException;
import java.net.InetSocketAddress;

import static org.junit.Assert.*;

/**
 * @author moonie
 * @date 2018/5/1
 */
public class ExpectedConditionsTest {
    @Test
    public void documentCompleted() throws Exception {
        WebViewController controller = null;
        try {
            controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://forum.qt.io/topic/54950/solved-print-qwebview-to-pdf/2");
            System.out.println(loadCommand.generate());
            String loadData = controller.sendCommand(loadCommand);
            System.out.println(loadData);

            Wait wait = new Wait(controller, 20000, 500);
            Boolean value = wait.until(ExpectedConditions.loadFinished());
            System.out.println(value);

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