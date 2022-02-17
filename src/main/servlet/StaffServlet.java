package main.servlet;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.Staff;
import main.service.IDeptService;
import main.service.IStaffService;
import main.service.impl.DepeService;
import main.service.impl.StaffService;

/*
 * 员工控制类
 */
public class StaffServlet extends BaseServlet{
	
	private IStaffService staffService = new StaffService();
	private IDeptService deptService = new DepeService();


	
	//登录验证
	public void loginCheck(HttpServletRequest req, HttpServletResponse res) {
		if(staffService.login(req)) {
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
	}
	
	//注册处理
	public void registerDeal(HttpServletRequest req, HttpServletResponse res) {
		responseObject(staffService.register(req), res);
	}

	//查询员工列表
	public void getStaffList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
		staffService.getStaffList(req);
		deptService.getDeptList(req);
		req.getRequestDispatcher("/WEB-INF/page/staff/staffList.jsp").forward(req, res);
	}

	public void updateStaff(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
		if(staffService.updateStaff(req) == 1){
			responseObject("1", res);
		}
		else{
			responseObject("0", res);
		}
	}
	public void deleteStaff(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
		if(staffService.deleteStaff(req) == 1){
			responseObject("1", res);
		}
		else{
			responseObject("0", res);
		}
	}

	public void addStaff(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
		if(staffService.addStaff(req) == 1){
			responseObject("1", res);
		}
		else{
			responseObject("0", res);
		}
	}

}
