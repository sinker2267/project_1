package main.dao.impl;

import main.dao.IDeptDao;
import main.model.Dept;
import main.model.Staff;
import main.util.DBHelper;
import main.util.StrUtil;

import java.util.List;

public class DeptDao implements IDeptDao {
    @Override
    public List<Dept> queryAll(Dept d) {
        StringBuffer sql = new StringBuffer("SELECT d.*,s.staff_name FROM dept as d LEFT JOIN staff as s on d.leader_id = s.id WHERE 1 = 1");
        if(d == null) return DBHelper.queryAll(sql.toString(), Dept.class, null);
        if (d.getId() != null) {
            sql.append(" and id = " + d.getId());
        }
        if(!StrUtil.isEmpty(d.getDeptName())) {
            sql.append(" and dept_name = '" + d.getDeptName() + "'");
        }
        if(d.getLeaderId() != null) {
            sql.append(" and leader_id = " + d.getLeaderId());
        }
        if(!StrUtil.isEmpty(d.getLeaderName())) {
            sql.append(" and leader_name = '" + d.getLeaderName() + "'");
        }
        return DBHelper.queryAll(sql.toString(), Dept.class, null);
    }
}
