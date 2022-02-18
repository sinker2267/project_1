package main.service;

import javax.servlet.http.HttpServletRequest;

import main.model.Staff;

import java.sql.SQLException;
import java.util.List;

/*
 * 员工业务接口
 */
public interface IStaffService {

	public boolean login(HttpServletRequest req);

	public int register(HttpServletRequest req);

	public void getStaffList(HttpServletRequest req) throws SQLException;

	public void getLeaderList(HttpServletRequest req);

	public void queryOne(HttpServletRequest req);

	public int updateStaff(HttpServletRequest req);

	public int deleteStaff(HttpServletRequest req);

	public int addStaff(HttpServletRequest req);

	public void getLeaderListNotLimit(HttpServletRequest req);
}
