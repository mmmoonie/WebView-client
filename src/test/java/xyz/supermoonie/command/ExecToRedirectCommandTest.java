package xyz.supermoonie.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import xyz.supermoonie.controller.WebViewController;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Base64;

/**
 *
 * Created by wangchao on 2018/4/19.
 */
public class ExecToRedirectCommandTest {

    @Test
    public void execToRedirect() {
        WebViewController controller = null;
        try {
            controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100), 10000, 30000);
            LoadCommand loadCommand = new LoadCommand("http://www.vgot.net/test/image2base64.php");
            String loadData = controller.sendCommand(loadCommand);
            System.out.println(loadData);

            String base64Image = "iVBORw0KGgoAAAANSUhEUgAAACgAAAAUCAYAAAD/Rn+7AAAACXBIWXMAAA7EAAAOxAGVKw4bAAADA0lEQVRIidVW227qMBBcX+IEk7bQ9Cah/v9v9bVSK4ECgaQNcx7C7LFDfwAkK8Hei3d2djciIkhXURRwzkFE4JyD917PnHMwxuge5bju7u4gIlgulyjLEiKisiGEzPbj46PqlWUJ5xyccyiKAkVRqB15eHhQ41VVQURgrYUxRg0sl0uICOq61v3n52e9pHNOZYqigLVW31M79/f3amcOTLqqqtIArdzAT2FniowxiDFmUb28vOg7z4gao22a5ooOPJ/TQRJEUxtE13tP/SkV5Mrr66ummcopDzebDUIImSM6r6oKi8UiowAXHVMmvXCMUX2UZTkHZ3pZrVaqRAFycrPZ/FkMMUa8vb0p6n8hxEDLssxkaCNdvCQLyDkHiTFmNx6GAfz1fZ8Rer1eQ0TQdR0AYL/f4+vrC33fZ6gaY1BVldo5n884Ho8AABYls+S9BwD8/v4CAMZxxMfHh/q6jSKRSwoAZJADwOFwyFJImRACvPeKftu2WdGktmKM2tv2+z3e39/17Hw+X9Gnqir8/PxMe2yeuiGiafDeo+975UtaOOny3mMcR714erFULsaI4/GoXK/rWptzyk9jDLquo61JkRGz3OVS3dxPO/+cRyKC3W6XVTs5RIe8OO0R0XnQ3B+GYdqz1uLz8/NKyFqLEAIOh4O2jvliSgFoYXCk8Zy90VqLrusgkvdUyhljVI9B1HV9I0UCQFEiEnKJbrvd4unpSTkUQtD28f39nXF0vgBgu92qvEg+h8lFABiGAeM4XhWqXpAb5A6fcwVyJoSgKT6dTpqepmmy5uy91/+pLVKB3OST/dM5NxVZjFErOEXCOafcoqP5JVOk5vqcMMzMYrFQP/wComxamLS3Wq1yBKlQFIUSm47ToU6kUhqkyBBVEUmdqBznLgNO28t6vYb3Xiu4aZobKBJrLQCgbduraDkrRfIJQhnSQJLe+AfJdd7O7UuSHSI/jqO2oQuV/iukQzvlHFPOM2D6UKCT+ZcMMH0gpM/UDp0DwOl0Qtu2WaVLUu3/AF2uuzULwio0AAAAAElFTkSuQmCC";
            String js = String.format("document.querySelector('textarea[name=base64code]').innerText = '%s';" +
                    "document.querySelector('option[value=png]')['selected'] = true;" +
                    "document.querySelector('form[name=form2] input[name=submit]').click();", base64Image);

            ExecToRedirectCommand execToRedirectCommand = new ExecToRedirectCommand(js);
            String execToRedirectData = controller.sendCommand(execToRedirectCommand);
            System.out.println(execToRedirectData);

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