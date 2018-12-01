package com.lancq;



import org.apache.commons.lang.StringUtils;

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

    public String getParameter(String key){
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
            System.out.println("客户端请求信息：******" + msg + "********");

            //解析出来uri
            int startIndex = msg.indexOf("GET") + 4;
            if(msg.contains("POST")){
                startIndex = msg.indexOf("POST") + 5;
            }
            int endIndex = msg.indexOf("HTTP/1.1") - 1;

            uri = msg.substring(startIndex, endIndex);

            String params = null;
            if(msg.startsWith("GET")){
                System.out.println("GET请求 uri：******" + uri + "********");
            } else if(msg.startsWith("POST")){
                params = msg.substring(msg.lastIndexOf("\n")+1);
            }
            if(!StringUtils.isEmpty(params)){
                if(params.contains("&")){
                    String[] tmp = params.split("&");
                    for(String s : tmp){
                        String[] tmpParam = s.split("=");
                        System.out.println(tmpParam[0] + "=" + tmpParam[1] );
                        paramMap.put(tmpParam[0],tmpParam[1]);
                    }
                } else {
                    String[] tmpParam = params.split("=");
                    System.out.println(tmpParam[0] + "=" + tmpParam[1] );
                    paramMap.put(tmpParam[0],tmpParam[1]);
                }
            }

        } else {
            System.out.println("Bad Request!");
        }
    }
}
