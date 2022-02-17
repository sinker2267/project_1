package main.servlet;


import main.service.impl.DepeService;
import main.service.impl.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageServlet extends BaseServlet{
    StaffService staffService = new StaffService();
    DepeService depeService = new DepeService();

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

    //跳转到更新员工信息页面
    public void updateStaffPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        staffService.queryOne(req);
        depeService.getDeptList(req);
        req.getRequestDispatcher("/WEB-INF/page/staff/updateStaff.jsp").forward(req, res);
    }

    //跳转到添加员工页面
    public void addStaffPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        depeService.getDeptList(req);
        req.getRequestDispatcher("/WEB-INF/page/staff/addStaff.jsp").forward(req, res);
    }

}
