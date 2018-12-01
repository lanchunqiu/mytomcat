package com.lancq;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author lancq
 * @Description
 * @Date 2018/9/11
 **/
public class HttpResponse {
    private OutputStream out = null;

    public HttpResponse(OutputStream out){
        this.out = out;
    }

    public void writeFile(String path) throws IOException {
        System.out.println("path = [" + path + "]");
        String rootPath = System.getProperty("user.dir");
        //System.out.println("rootPath = [" + rootPath + "]");
        FileInputStream fis = new FileInputStream(rootPath + "\\mytomcat2\\src\\main\\webapp\\" + path);
        //System.out.println("fis = [" + fis + "]");
        byte[] buff = new byte[1024];
        int len = 0;
        //构建一个响应头信息
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\n");
        sb.append("Content-Type: text/html;charset=UTF-8\n");
        sb.append("\r\n");
        //写入响应头
        out.write(sb.toString().getBytes());

        //写文件信息
        while((len=fis.read(buff)) != -1){
            out.write(buff,0, len);
        }
        fis.close();
        out.flush();
        out.close();
    }

}
