package main.dao;

import main.model.Dept;

import java.util.List;

public interface IDeptDao {
    public Dept queryOne(Dept d);
    public List<Dept> queryAll(Dept d);
}
