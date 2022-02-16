package main.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.dao.IStaffDao;
import main.dao.impl.StaffDao;
import main.model.Staff;
import main.service.IStaffService;
import main.util.DBHelper;
import main.util.MD5Util;
import main.util.StrUtil;

import java.util.List;

public class StaffService implements IStaffService{

	private IStaffDao staffDao = new StaffDao();
	
	@Override
	public boolean login(HttpServletRequest req) {
		String pass = MD5Util.encode(req.getParameter("pass"));
		String phone = req.getParameter("phone");
		Staff s = new Staff();
		if(phone != null) s.setPhone(phone); 
		s.setPass(pass);
		Staff staff = staffDao.queryOne(s);
		if(staff == null) {
			//登陆失败
			return false;
		}
		//向session添加用户信息
		HttpSession session = req.getSession();
		session.setAttribute("staff", staff);
		session.setMaxInactiveInterval(10);
		return true;
	}

	@Override
	public int register(HttpServletRequest req) {
		//响应刷新输出流
		String staffName = req.getParameter("name");
		String staffMoblie = req.getParameter("moblie");
		String staffPass = MD5Util.encode(req.getParameter("psd"));

		Staff staff = new Staff();
		staff.setStaffName(staffName);
		staff.setPass(staffPass);
		staff.setPhone(staffMoblie);
		if(staffDao.add(staff) == 1){
			HttpSession session = req.getSession();
			session.setAttribute("staff", staff);
			session.setMaxInactiveInterval(10);
			//保存到数据库中
			return 1;
		}
		return 0;
	}

	@Override
	public void getStaffList(HttpServletRequest req) {
		Staff staff = new Staff();
		String deptId = req.getParameter("deptId");
		if(!StrUtil.isEmpty(deptId)){
			staff.setDeptId(Integer.parseInt(deptId));
		}
		String tmpId = req.getParameter("id");
		if(!StrUtil.isEmpty(tmpId)){
			staff.setId(Integer.parseInt(tmpId));
		}
		String phone = req.getParameter("phone");
		if(!StrUtil.isEmpty(phone)){
			staff.setPhone(phone);
		}
		String staffName = req.getParameter("staffName");
		if(!StrUtil.isEmpty(staffName)){
			staff.setStaffName(staffName);
		}
		List<Staff> staffList = staffDao.queryAll(staff);
		req.setAttribute("staff",staffList);
	}

}
