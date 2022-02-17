package main.dao.impl;

import java.sql.SQLException;
import java.util.List;

import main.dao.IStaffDao;
import main.model.Staff;
import main.util.DBHelper;
import main.util.StrUtil;

public class StaffDao implements IStaffDao {

	@Override
	public Staff queryOne(Staff s) {
		List<Staff> list = queryAll(s, null, null);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Staff> queryAll(Staff s, Integer pageNo, Integer pageCount) {
		StringBuffer sql = new StringBuffer("SELECT s.*,d.dept_name FROM staff as s LEFT JOIN dept as d " +
				"on s.dept_id = d.id WHERE 1 = 1");
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
		if(!StrUtil.isEmpty(pageCount) && !StrUtil.isEmpty(pageNo)){
			sql.append(" LIMIT " + (pageNo-1)*pageCount + "," + pageCount);
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

	@Override
	public List<Staff> queryLeader(Staff s) {
		String sql = "SELECT staff.*,dept.dept_name FROM staff , dept where staff.id = dept.leader_id";
		return DBHelper.queryAll(sql, Staff.class, null);
	}

	@Override
	public int countAll(Staff s) throws SQLException {
		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM staff as s LEFT JOIN dept as d " +
				"on s.dept_id = d.id WHERE 1 = 1");
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
		return DBHelper.countAll(sql.toString(), null);
	}

	@Override
	public int updateStaff(Staff s) {
		StringBuffer sql = new StringBuffer("update staff set");
		if(!StrUtil.isEmpty(s.getPhone())){
			sql.append(" phone = '" + s.getPhone() +"',");
		}
		if(!StrUtil.isEmpty(s.getStaffName())){
			sql.append(" staff_name = '" + s.getStaffName() + "',");
		}
		if(s.getDeptId() != null){
			sql.append(" dept_id = " + s.getDeptId());
		}
		sql.append(" where id = " + s.getId());
		//System.out.println(sql);
		return DBHelper.deal(sql.toString(),null);
	}

}
