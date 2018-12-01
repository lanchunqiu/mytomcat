package com.lancq;

import java.io.IOException;

/**
 * @Author lancq
 * @Description
 * @Date 2018/9/12
 **/
public class LoginServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if("lancq".equals(username) && "123456".equals(password)){
            response.writeFile("/welcome.html");
        } else {
            response.writeFile("/error.html");
        }
    }
}
