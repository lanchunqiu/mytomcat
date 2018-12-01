package com.lancq;


import java.io.IOException;

/**
 * @Author lancq
 * @Description
 * @Date 2018/9/13
 **/
public class RegisterServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if(null != username && null != password && password.equals(confirmPassword)){
            System.out.println("成功注册用户" + username);
            response.writeFile("/success.html");
        } else {
            response.writeFile("/error.html");
        }
    }
}
