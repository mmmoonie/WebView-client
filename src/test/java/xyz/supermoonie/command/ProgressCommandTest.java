package xyz.supermoonie.command;

import org.junit.Test;
import xyz.supermoonie.controller.WebViewController;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author moonie
 * @date 2018/4/30
 */
public class ProgressCommandTest {

    @Test
    public void progress() {
        WebViewController controller = null;
        try {
            controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
            System.out.println(loadCommand.generate());
            String loadData = controller.sendCommand(loadCommand);
            System.out.println(loadData);

            ProgressCommand progressCommand = new ProgressCommand();
            System.out.println(progressCommand.generate());
            String progressData = controller.sendCommand(progressCommand);
            System.out.println(progressData);

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