package main.servlet;


import main.service.impl.DepeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageServlet extends BaseServlet{

    //跳转到登录页面
    public void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
    }
    //跳转到主页
    public void mainPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/page/main.jsp").forward(req, res);
    }

    //跳转到注册页面
    public void registerPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/page/register.jsp").forward(req, res);
    }

}
