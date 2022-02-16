package main.dao;

import main.model.Staff;

import java.util.List;

public interface IStaffDao {
    Staff queryOne(Staff s);

    List<Staff> queryAll(Staff s);

    int update(Staff s);

    int add(Staff s);
}
