package xyz.supermoonie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.supermoonie.command.AbstractCommand;
import xyz.supermoonie.exception.WebViewClientException;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * WebViewSpider 驱动
 *
 * @author Administrator
 * @date 2018/4/18
 */
public class WebViewDriver implements Closeable{

    /**
     * 分隔符
     */
    private static final String BOUNDARY = "boundary-----------";
    private static final String CODE_KEY = "code";
    private static final String DATA_KEY = "data";
    private static final int OK_CODE = 200;

    /**
     * 通过 socket 与 NettySpiderServer 建立链接
     */
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public WebViewDriver(SocketAddress socketAddress) throws IOException {
        this(socketAddress, 10000, 10000);
    }

    public WebViewDriver(SocketAddress socketAddress, int connectTimeOut, int soTimeOut) throws IOException {
        socket = new Socket();
        socket.connect(socketAddress, connectTimeOut);
        socket.setSoTimeout(soTimeOut);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public String read() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        String info;
        while ((info = reader.readLine()) != null) {
            if (info.trim().equals(BOUNDARY)) {
                break;
            }
            stringBuilder.append(info);
        }
        return stringBuilder.toString();
    }

    public void write(AbstractCommand command) throws IOException {
        String cmd = command.generate();
        writer.write(cmd + BOUNDARY);
        writer.flush();
    }

    public String sendCommand(AbstractCommand command) throws WebViewClientException, IOException {
        write(command);
        String result = read();
        try {
            JSONObject json = JSON.parseObject(result);
            if (json.containsKey(CODE_KEY) && json.getIntValue(CODE_KEY) == OK_CODE) {
                return json.getString(DATA_KEY);
            } else {
                throw new WebViewClientException("code is not 200, " + result);
            }
        } catch (Exception e) {
            throw new WebViewClientException(e.getMessage() + " , result: " + result);
        }
    }

    @Override
    public void close() throws IOException {
        socket.shutdownOutput();
        socket.shutdownInput();
        reader.close();
        writer.close();
        socket.close();
    }


}
