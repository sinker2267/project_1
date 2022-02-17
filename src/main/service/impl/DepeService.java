package main.service.impl;

import main.dao.impl.DeptDao;
import main.model.Dept;
import main.service.IDeptService;
import main.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DepeService implements IDeptService {
    private DeptDao deptDao = new DeptDao();
    @Override
    public void getDeptList(HttpServletRequest req) {
        Dept dept = new Dept();
        String id = req.getParameter("deptid");
        String deptName =  req.getParameter("deptName");
        String leaderId =  req.getParameter("leaderId");
        if(!StrUtil.isEmpty(id)){
            dept.setId(Integer.parseInt(id));
        }
        if(!StrUtil.isEmpty(deptName)){
            dept.setDeptName(deptName);
        }
        if(!StrUtil.isEmpty(leaderId)){
            dept.setLeaderId(Integer.parseInt(leaderId));
        }
        req.setAttribute("deptList",deptDao.queryAll(dept));
    }
}
