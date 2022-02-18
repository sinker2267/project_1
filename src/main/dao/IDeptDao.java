package main.dao;

import main.model.Dept;
import main.model.Staff;
;

import java.sql.SQLException;
import java.util.List;

public interface IDeptDao {
    public Dept queryOne(Dept d) throws SQLException;

    public List<Dept> queryAll(Dept d, Integer pageNo, Integer pageCount) throws SQLException;

    public List<Dept> queryAllNotLimit(Dept d);

    int countAll(Dept d) throws SQLException;

    int updateDept(Dept d);

    int deleteDept(Dept d);

    int addDept(Dept d);

    Long queryNum(Integer id) throws SQLException;
}
