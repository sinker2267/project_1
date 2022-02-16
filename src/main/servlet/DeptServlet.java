package main.servlet;

import main.service.impl.DepeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeptServlet extends BaseServlet{
    DepeService depeService = new DepeService();
    public void getDeptList(HttpServletRequest req, HttpServletResponse res){
        depeService.getDeptList(req);
    }
}
