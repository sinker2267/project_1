package main.service;

import javax.servlet.http.HttpServletRequest;

import main.model.Staff;

import java.util.List;

/*
 * 员工业务接口
 */
public interface IStaffService {

	public boolean login(HttpServletRequest req);

	public int register(HttpServletRequest req);

	public void getStaffList(HttpServletRequest req);
}
