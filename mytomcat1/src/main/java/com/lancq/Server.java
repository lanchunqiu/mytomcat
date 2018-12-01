package com.lancq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author lancq
 * @Description 手写Tomcat 1.0
 * @Date 2018/9/11
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        //服务端初始化监听端口
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("服务端初始化完毕，监听端口为" + 8000);
        //不断在监听客户端连接
        while (true){
            //服务端等待客户端的socket的请求过来
            Socket client = serverSocket.accept();

            //对客户端请求信息进行处理
            InputStream in = client.getInputStream();

            //定义一个读取的缓冲区，主要是在InputStream流当中读取字节
            byte[] buff = new byte[1024];
            int len = in.read(buff);
            if(len > 0){
                //将读取处理的字节信息转化为明文信息
                String msg = new String(buff,0, len);
                System.out.println("客户端请求信息：******" + msg + "********");

                //输出服务端响应信息
                OutputStream out = client.getOutputStream();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //构建一个响应头信息
                StringBuffer sb = new StringBuffer();
                sb.append("HTTP/1.1 200 OK\n");
                sb.append("Content-Type: text/html;charset=UTF-8\n");
                sb.append("\r\n");
                //响应正文
                String html = "<html>"
                        +"<head>"
                        +"<title>"
                        +"欢迎"
                        +"</title>"
                        +"</head>"
                        +"<body>"
                        +"当前时间："
                        +"<font size='14' color='blue'>"
                        +format.format(new Date())
                        +"</font>"
                        +"<br/>"
                        +"服务器回复：<font size='14' color='black'>今天你有收获吗？</font>"
                        +"</body>"
                        +"</html>";
                sb.append(html);
                out.write(sb.toString().getBytes("UTF-8"));
                out.flush();
                out.close();
                //关闭客户端，到这里一个请求和一个响应完成
                client.close();
            }
        }



    }
}
