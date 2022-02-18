package main.dao.impl;

import main.dao.IDeptDao;
import main.model.Dept;
import main.util.DBHelper;
import main.util.StrUtil;

import java.sql.SQLException;
import java.util.List;

public class DeptDao implements IDeptDao {
    @Override
    public Dept queryOne(Dept d) {
        return queryAll(d,null,null).get(0);
    }

    @Override
    public List<Dept> queryAll(Dept d, Integer pageNo, Integer pageCount) {
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
        if(!StrUtil.isEmpty(pageCount) && !StrUtil.isEmpty(pageNo)){
            sql.append(" LIMIT " + (pageNo-1)*pageCount + "," + pageCount);
        }
        return DBHelper.queryAll(sql.toString(), Dept.class, null);
    }

    @Override
    public int countAll(Dept d) throws SQLException {
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM dept as d LEFT JOIN staff as s " +
                "on s.id = d.leader_id WHERE 1 = 1");
        if (d.getId() != null) {
            sql.append(" and d.id = " + d.getId());
        }
        if(!StrUtil.isEmpty(d.getDeptName())) {
            sql.append(" and d.dept_name = '" + d.getDeptName() + "'");
        }
        if(d.getLeaderId() != null) {
            sql.append(" and d.leader_id = " + d.getLeaderId());
        }
        return DBHelper.countAll(sql.toString(), null);
    }

    @Override
    public int updateDept(Dept d) {
        StringBuffer sql = new StringBuffer("update dept set");
        if(!StrUtil.isEmpty(d.getDeptName())){
            sql.append(" dept_name = '" + d.getDeptName() +"',");
        }

        if(d.getLeaderId() != null){
            sql.append(" leader_id = " + d.getLeaderId() + ",");
        }

        sql.append(" id = " + d.getId());
        sql.append(" where id = " + d.getId());
        return DBHelper.deal(sql.toString(),null);
    }

    @Override
    public int deleteDept(Dept d) {
        boolean and = false;
        StringBuffer sql = new StringBuffer("delete from dept where ");
        if(d.getId() != null){
            if(and) sql.append(" and");
            sql.append(" id = " + d.getId());
            and = true;
        }
        if(!StrUtil.isEmpty(d.getDeptName())){
            if(and) sql.append(" and");
            sql.append(" and dept_name = '" + d.getDeptName() +"'");
            and = true;
        }
        if(d.getLeaderId() != null){
            if(and) sql.append(" and");
            sql.append(" and leader_id = " + d.getLeaderId() + "");
            and = true;
        }

        return DBHelper.deal(sql.toString(),null);
    }

    @Override
    public int addDept(Dept d) {
        boolean lflag = false;
        boolean rflag = false;
        StringBuffer lsql = new StringBuffer("insert into dept (");
        StringBuffer rsql = new StringBuffer(" values (");
        if(d.getId() != null){
            if(lflag) lsql.append(",");
            lsql.append("id");
            if(rflag) rsql.append(",");
            rsql.append(d.getId());
            lflag = true;
            rflag = true;
        }
        if(!StrUtil.isEmpty(d.getDeptName())){
            if(lflag) lsql.append(",");
            lsql.append("dept_name");
            if(rflag) rsql.append(",");
            rsql.append("'"+d.getDeptName()+"'");
            lflag = true;
            rflag = true;
        }
        if(d.getLeaderId() != null){
            if(lflag) lsql.append(",");
            lsql.append("leader_id");
            if(rflag) rsql.append(",");
            rsql.append(d.getLeaderId());
            lflag = true;
            rflag = true;
        }
        lsql.append(")");
        rsql.append(")");
        return DBHelper.deal(lsql.append(rsql).toString(), null);
    }
}
