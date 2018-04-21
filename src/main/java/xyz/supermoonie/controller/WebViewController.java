package xyz.supermoonie.controller;

import xyz.supermoonie.command.AbstractCommand;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author Administrator
 * @date 2018/4/18 0018
 */
public class WebViewController implements Closeable{

    private static final String BOUNDARY = "boundary-----------";

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public WebViewController(SocketAddress socketAddress) throws IOException {
        this(socketAddress, 10000, 10000);
    }

    public WebViewController(SocketAddress socketAddress, int connectTimeOut, int soTimeOut) throws IOException {
        socket = new Socket();
        socket.connect(socketAddress, connectTimeOut);
        socket.setSoTimeout(soTimeOut);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public String sendCommand(AbstractCommand command) throws IOException {
        writer.write(command.generate());
        writer.flush();
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

    @Override
    public void close() throws IOException {
        socket.shutdownOutput();
        socket.shutdownInput();
        reader.close();
        writer.close();
        socket.close();
    }


}
