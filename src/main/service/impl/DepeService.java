package main.service.impl;

import main.dao.impl.DeptDao;
import main.model.Dept;
import main.service.IDeptService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DepeService implements IDeptService {
    private DeptDao deptDao = new DeptDao();
    @Override
    public void getDeptList(HttpServletRequest req) {
        req.setAttribute("deptList",deptDao.queryAll(null));
    }
}
