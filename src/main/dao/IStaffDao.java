package main.dao;

import main.model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface IStaffDao {
    Staff queryOne(Staff s);

    List<Staff> queryAll(Staff s, Integer pageNo, Integer pageCount);

    int update(Staff s);

    int add(Staff s);

    List<Staff> queryLeader(Staff s);

    int countAll(Staff s) throws SQLException;

    int updateStaff(Staff s);

    int deleteStaff(Staff s);

    int addStaff(Staff s);
}
