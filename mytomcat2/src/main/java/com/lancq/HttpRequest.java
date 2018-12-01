package com.lancq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lancq
 * @Description
 * @Date 2018/9/11
 **/
public class HttpRequest {
    //客户端请求uri
    private String uri;

    //参数缓存
    private Map<String,String> paramMap = new ConcurrentHashMap<String,String>();

    public String getValue(String key){
        return paramMap.get(key);
    }

    public String getUri() {
        return uri;
    }

    public HttpRequest(InputStream inputStream) throws IOException {
        resolverRequest(inputStream);
    }

    private void resolverRequest(InputStream in) throws IOException {
        //定义一个读取的缓冲区，主要是在InputStream流当中读取字节
        byte[] buff = new byte[1024];
        int len = in.read(buff);
        if(len > 0){
            //将读取处理的字节信息转化为明文信息
            String msg = new String(buff,0, len);
            //System.out.println("客户端请求信息：******" + msg + "********");

            //解析出来uri
            uri = msg.substring(msg.indexOf("/"), msg.indexOf(" HTTP/1.1"));
            System.out.println("uri：******" + uri + "********");

        } else {
            System.out.println("Bad Request!");
        }
    }
}
