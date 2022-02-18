package main.service.impl;

import main.dao.impl.DeptDao;
import main.model.Dept;
import main.model.Staff;
import main.service.IDeptService;
import main.util.MD5Util;
import main.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DepeService implements IDeptService {
    private DeptDao deptDao = new DeptDao();
    @Override
    public void getDeptList(HttpServletRequest req) throws SQLException {
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
        req.setAttribute("queryDeptDemo",dept);
        Integer pageNo = new Integer(1);
        Integer pageCount = new Integer(4);
        Integer totalCount = deptDao.countAll(dept);
        if(!StrUtil.isEmpty(req.getParameter("pageNo")) && !StrUtil.isEmpty(req.getParameter("pageCount"))){
            pageCount = Integer.parseInt(req.getParameter("pageCount"));
            pageNo = Math.min(Integer.parseInt(req.getParameter("pageNo")), (totalCount + (pageCount - 1)) / pageCount);
        }
        req.setAttribute("pageNo",pageNo);
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("deptList",deptDao.queryAll(dept,pageNo,pageCount));
    }

    @Override
    public void queryOne(HttpServletRequest req) {
        Dept dept = new Dept();
        Integer id = Integer.parseInt(req.getParameter("id"));
        dept.setId(id);
        req.setAttribute("updateDept",deptDao.queryOne(dept));
    }

    @Override
    public int updateDept(HttpServletRequest req) {
        String id = req.getParameter("id");
        String deptName = req.getParameter("deptName");
        String leaderId = req.getParameter("leaderId");

        Dept dept = new Dept();
        dept.setId(Integer.parseInt(id));
        dept.setDeptName(deptName);
        dept.setLeaderId(Integer.parseInt(leaderId));

        return deptDao.updateDept(dept);
    }

    @Override
    public int deleteDept(HttpServletRequest req) {
        String id = req.getParameter("id");
        Dept dept = new Dept();
        dept.setId(Integer.parseInt(id));
        return deptDao.deleteDept(dept);
    }

    @Override
    public int addDept(HttpServletRequest req) {
        String deptName = req.getParameter("deptName");
        Integer leaderId = null;
        if(!StrUtil.isEmpty(req.getParameter("leaderId"))){
            leaderId = Integer.parseInt(req.getParameter("leaderId"));
        }
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setLeaderId(leaderId);
        return deptDao.addDept(dept);
    }
}
