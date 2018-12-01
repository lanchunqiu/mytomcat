package com.lancq;

import java.io.IOException;

/**
 * @Author lancq
 * @Description
 * @Date 2018/9/11
 **/
public interface HttpServlet {
    void service(HttpRequest request, HttpResponse response) throws IOException;
}
