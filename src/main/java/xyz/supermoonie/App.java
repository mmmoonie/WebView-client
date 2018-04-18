package xyz.supermoonie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.command.*;
import xyz.supermoonie.controller.WebViewController;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Base64;

/**
 * Hello world!
 *
 * @author Administrator
 */
public class App {
    public static void main(String[] args) {
        WebViewController controller = null;
        try {
            controller = new WebViewController(new InetSocketAddress("127.0.0.1", 7100));
            LoadCommand loadCommand = new LoadCommand("https://persons.shgjj.com");
            String loadData = controller.sendCommand(loadCommand);
            System.out.println(loadData);

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
//            LoadWithProxyCommand loadWithProxyCommand = new LoadWithProxyCommand("https://persons.shgjj.com", proxy);
//            String loadData = controller.sendCommand(loadWithProxyCommand);
//            System.out.println(loadData);

            String cookieData = controller.sendCommand(new GetCookieCommand());
            System.out.println(cookieData);
            CaptchaCommand captchaCommand = new CaptchaCommand("img[src=VerifyImageServlet]");
            String captchaData = controller.sendCommand(captchaCommand);
            System.out.println(captchaData);
            JSONObject captchaJson = JSON.parseObject(captchaData);
            String base64Image = captchaJson.getString("data");
            byte[] bytes = Base64.getDecoder().decode(base64Image);
            String account = JOptionPane.showInputDialog(null, "account");
            String password = JOptionPane.showInputDialog(null, "password");
            String captcha = (String) JOptionPane.showInputDialog(null, "captcha", "captcha", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(bytes), null, null);
            String js = String.format("$('input[name=username]').val('%s');" +
                    "$('input[name=password]').val('%s');" +
                    "$('input[name=imagecode]').val('%s');" +
                    "login_submit(loginform);", account, password, captcha);
            ExecToRedirectCommand loginCommand = new ExecToRedirectCommand(js);
            String loginData = controller.sendCommand(loginCommand);
            System.out.println(loginData);
        } catch (IOException e) {
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
