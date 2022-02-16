package main.service;

import javax.servlet.http.HttpServletRequest;

public interface IDeptService {
    //查询部门列表
    public void getDeptList(HttpServletRequest req);
}
