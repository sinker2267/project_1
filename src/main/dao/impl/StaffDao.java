package main.dao.impl;

import java.util.List;

import main.dao.IStaffDao;
import main.model.Staff;
import main.util.DBHelper;
import main.util.StrUtil;

public class StaffDao implements IStaffDao {

	@Override
	public Staff queryOne(Staff s) {
		List<Staff> list = queryAll(s);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Staff> queryAll(Staff s) {
		StringBuffer sql = new StringBuffer("SELECT s.*,d.dept_name FROM staff as s LEFT JOIN dept as d " +
				"on s.dept_id = d.id WHERE 1 = 1");
		if(s == null) return DBHelper.queryAll(sql.toString(), Staff.class, null);
		if (s.getId() != null) {
			sql.append(" and s.id = " + s.getId());
		}
		if(!StrUtil.isEmpty(s.getStaffName())) {
			sql.append(" and s.staff_name = '" + s.getStaffName() + "'");
		}
		if(!StrUtil.isEmpty(s.getPass())) {
			sql.append(" and s.pass = '" + s.getPass() + "'");
		}
		if(!StrUtil.isEmpty(s.getPhone())) {
			sql.append(" and s.phone = '" + s.getPhone() + "'");
		}
		if(s.getDeptId() != null) {
			sql.append(" and s.dept_id = " + s.getDeptId());
		}
		return DBHelper.queryAll(sql.toString(), Staff.class, null);
	}

	@Override
	public int update(Staff s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Staff s) {
		// TODO Auto-generated method stub
		String sql = "insert into staff (staff_name, pass, phone) values(?,?,?)";
		return DBHelper.deal(sql, s.getStaffName(), s.getPass(), s.getPhone());
	}
}
