package main.dao;

import main.model.Dept;
import main.model.Staff;
;

import java.sql.SQLException;
import java.util.List;

public interface IDeptDao {
    public Dept queryOne(Dept d);

    public List<Dept> queryAll(Dept d, Integer pageNo, Integer pageCount);

    int countAll(Dept d) throws SQLException;

    int updateDept(Dept d);
}
