<%--
  Created by IntelliJ IDEA.
  User: 17864
  Date: 2022/2/12
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
</head>
<body>
<div class="layui-container layui-col-space15" style="margin: 20px auto; width: 100%;">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">查询</div>
                <div class="layui-card-body">
                    <form class="layui-form" action="StaffServlet?method=getStaffList" method="POST">
                        <div class="layui-form-item layui-inline" style="width: 20%;">
                            <input type="text" name="id" placeholder="请输入员工编号" class="layui-input">
                        </div>
                        <div class="layui-form-item layui-inline" style="width: 20%;">
                            <input type="text" name="staffName" placeholder="请输入员工姓名" class="layui-input">
                        </div>
                        <div class="layui-form-item layui-inline" style="width: 20%;">
                            <input type="text" name="phone" placeholder="请输入手机号" class="layui-input">
                        </div>
                        <div class="layui-form-item layui-inline" style="width: 20%;">
                            <select name="deptId">
                                <option value="">部门</option>
                                <c:forEach items="${requestScope.deptList}" var="dept">
                                    <option value="${dept.id}">${dept.deptName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="layui-form-item layui-inline">
                            <button class="layui-btn">查询</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">员工列表</div>
                <div class="layui-card-body">
                    <div>
                        <button class="layui-btn layui-btn-sm" onclick="openWin();">添加</button>
                    </div>
                    <table class="layui-table">
                        <!-- 设置每一个单元格的占得宽度 -->
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="30%">
                            <col width="20%">
                            <col width="30%">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>员工编号</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.staff}" var="s">
                            <tr>
                                <td>${s.id }</td>
                                <td>${s.staffName }</td>
                                <td>${s.phone }</td>
                                <td>${s.deptName }</td>
                                <td>
<%--                                    <button class="layui-btn layui-btn-sm" onclick="openWin()">更新</button>--%>
                                    <button class="layui-btn layui-btn-primary layui-btn-sm" onclick=""><i class="layui-icon">&#xe642;</i></button>
                                    <button class="layui-btn layui-btn-primary layui-btn-sm" onclick=""><i class="layui-icon">&#xe640;</i></button>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <!-- 分页样式 -->
                    <div id="lp" align="center"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
    //加载模块（组件）
    layui.use(['layer','laypage'], function(){
        //创建弹出层对象
        var layer = layui.layer;
        //创建分页对象
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'lp' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: 50 //数据总数，从服务端得到
            ,limit: 10 //每页显示的条数，laypage将会借助 count 和 limit 计算出分页数。
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); //得到每页显示的条数

                //首次不执行
                if(!first){
                    //do something
                }
            }
        });
    });

    function openWin(){
        layer.open({
            type: 2,
            title: "员工信息",
            area: ['450px', '530px'],
            content: "user_update.html"
        });
    }

</script>
</html>

