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

import java.sql.SQLException;
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
	public void getStaffList(HttpServletRequest req) throws SQLException {
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
		req.setAttribute("queryDemo",staff);
		Integer pageNo = new Integer(1);
		Integer pageCount = new Integer(4);
		Integer totalCount = staffDao.countAll(staff);
		if(!StrUtil.isEmpty(req.getParameter("pageNo")) && !StrUtil.isEmpty(req.getParameter("pageCount"))){
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageCount = Integer.parseInt(req.getParameter("pageCount"));
		}
		req.setAttribute("pageNo",pageNo);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("totalCount",totalCount);
		List<Staff> staffList = staffDao.queryAll(staff, pageNo, pageCount);
		req.setAttribute("staff",staffList);
	}

	@Override
	public void getLeaderList(HttpServletRequest req) {
		Staff staff = new Staff();
		req.setAttribute("leaderList", staffDao.queryLeader(staff));
	}

	@Override
	public void queryOne(HttpServletRequest req) {
		Staff staff = new Staff();
		Integer id = Integer.parseInt(req.getParameter("id"));
		staff.setId(id);
		req.setAttribute("updateStaff",staffDao.queryOne(staff));
	}

	@Override
	public int updateStaff(HttpServletRequest req) {
		String id = req.getParameter("id");
		String staffName = req.getParameter("staffName");
		String phone = req.getParameter("phone");
		String deptId = req.getParameter("deptId");
		Staff staff = new Staff();
		staff.setId(Integer.parseInt(id));;
		staff.setStaffName(staffName);
		staff.setPhone(phone);
		staff.setDeptId(Integer.parseInt(deptId));
		return staffDao.updateStaff(staff);
	}

}
