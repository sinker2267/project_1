package main.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.dao.IDeptDao;
import main.dao.IStaffDao;
import main.dao.impl.DeptDao;
import main.dao.impl.StaffDao;
import main.model.Dept;
import main.model.Staff;
import main.service.IStaffService;
import main.util.DBHelper;
import main.util.MD5Util;
import main.util.StrUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StaffService implements IStaffService{

	private IStaffDao staffDao = new StaffDao();
	private IDeptDao deptDao = new DeptDao();
	
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
			pageCount = Integer.parseInt(req.getParameter("pageCount"));
			pageNo = Math.min(Integer.parseInt(req.getParameter("pageNo")), (totalCount + (pageCount - 1)) / pageCount);
		}
		req.setAttribute("pageNo",pageNo);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("totalCount",totalCount);
		req.setAttribute("staff",staffDao.queryAll(staff, pageNo, pageCount));
	}

	@Override
	public void getLeaderList(HttpServletRequest req) {
		Staff staff = new Staff();
		List<Staff> list = staffDao.queryLeader(staff);
		req.setAttribute("leaderList", list);
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
		String pass = req.getParameter("pass");
		Staff staff = new Staff();
		staff.setId(Integer.parseInt(id));;
		staff.setStaffName(staffName);
		staff.setPhone(phone);
		staff.setDeptId(Integer.parseInt(deptId));
		staff.setPass(pass);
		return staffDao.updateStaff(staff);
	}

	@Override
	public int deleteStaff(HttpServletRequest req) {
		String id = req.getParameter("id");
		Staff staff = new Staff();
		staff.setId(Integer.parseInt(id));
		return staffDao.deleteStaff(staff);
	}

	@Override
	public int addStaff(HttpServletRequest req) {
		String staffName = req.getParameter("staffName");
		String pass = MD5Util.encode(req.getParameter("pass"));
		String phone= req.getParameter("phone");
		Integer deptId = null;
		String deptName = null;
		if(!StrUtil.isEmpty(req.getParameter("deptId"))){
			deptId = Integer.parseInt(req.getParameter("deptId"));
		}
		Staff staff = new Staff();
		staff.setStaffName(staffName);
		staff.setDeptName(deptName);
		staff.setPass(pass);
		staff.setPhone(phone);
		staff.setDeptId(deptId);
		return staffDao.addStaff(staff);
	}

}
