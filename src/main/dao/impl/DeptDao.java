package main.dao.impl;

import main.dao.IDeptDao;
import main.model.Dept;
import main.util.DBHelper;
import main.util.StrUtil;

import java.util.List;

public class DeptDao implements IDeptDao {
    @Override
    public Dept queryOne(Dept d) {
        return queryAll(d).get(0);
    }

    @Override
    public List<Dept> queryAll(Dept d) {
        StringBuffer sql = new StringBuffer("SELECT d.*,s.staff_name as leader_name FROM dept as d LEFT JOIN staff as s on d.leader_id = s.id WHERE 1 = 1");
        if (d.getId() != null) {
            sql.append(" and d.id = " + d.getId());
        }
        if(!StrUtil.isEmpty(d.getDeptName())) {
            sql.append(" and d.dept_name = '" + d.getDeptName() + "'");
        }
        if(d.getLeaderId() != null) {
            sql.append(" and d.leader_id = " + d.getLeaderId());
        }
        if(!StrUtil.isEmpty(d.getLeaderName())) {
            sql.append(" and d.leader_name = '" + d.getLeaderName() + "'");
        }
        return DBHelper.queryAll(sql.toString(), Dept.class, null);
    }
}
