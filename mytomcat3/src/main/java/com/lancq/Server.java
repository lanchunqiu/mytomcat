package com.lancq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author lancq
 * @Description 手写Tomcat 3.0
 * @Date 2018/9/11
 **/
public class Server {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            //服务端初始化监听端口
            serverSocket = new ServerSocket(8000);
            System.out.println("服务端初始化完毕，监听端口为" + 8000);
            //不断在监听客户端连接
            while (true){
                //服务端等待客户端的socket的请求过来
                client = serverSocket.accept();
                //对客户端请求信息进行处理
                InputStream in = client.getInputStream();
                HttpRequest request = new HttpRequest(in);
                String requestUri = request.getUri();
                System.out.println("requestUri = [" + requestUri + "]");
                OutputStream out = client.getOutputStream();
                HttpResponse response = new HttpResponse(out);

                //传入uri: /index.html
                //这里需要判断是否是静态资源
                boolean flag = isStatic(requestUri);
                if(flag){
                    response.writeFile(requestUri.substring(1));
                } else {
                    HttpServlet servlet = new LoginServlet();
                    servlet.service(request,response);
                }
                //关闭客户端，到这里一个请求和一个响应完成
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isStatic(String requestUri) {
        boolean flag = false;
        String[] subfix = {"html","css","jpg","jpeg","png","tiff","gif"};
        for(String s : subfix){
            if(requestUri.endsWith(s)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
