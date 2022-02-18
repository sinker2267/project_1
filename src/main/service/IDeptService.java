package main.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface IDeptService {
    //查询部门列表
    public void getDeptList(HttpServletRequest req) throws SQLException;

    public void queryOne(HttpServletRequest req);

    public int updateDept(HttpServletRequest req);

    public int deleteDept(HttpServletRequest req);

    public int addDept(HttpServletRequest req);
}
