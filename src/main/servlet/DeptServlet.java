package main.servlet;

import main.service.impl.DepeService;
import main.service.impl.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeptServlet extends BaseServlet{
    DepeService depeService = new DepeService();
    StaffService staffService = new StaffService();
    public void getDeptList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
        depeService.getDeptList(req);
        staffService.getLeaderList(req);
        req.getRequestDispatcher("/WEB-INF/page/dept/deptList.jsp").forward(req, res);
    }
    public void updateDept(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
        if(depeService.updateDept(req) == 1){
            responseObject("1", res);
        }
        else{
            responseObject("0", res);
        }
    }
}
