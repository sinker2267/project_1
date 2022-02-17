<%--
  Created by IntelliJ IDEA.
  User: 17864
  Date: 2022/2/17
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
</head>
<body>
<div class="site-text" style="margin: 5%;" id="window" >
  <form class="layui-form" id="book"  lay-filter="example" onsubmit="return false">
    <div class="layui-form-item">
      <label class="layui-form-label">员工编号</label>
      <div class="layui-input-block"style="width: 60%;">
        <input type="text" id="bid" name="id" value="${requestScope.updateStaff.id}" lay-verify="title" class="layui-input" readonly>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">姓名</label>
      <div class="layui-input-block"style="width: 60%;">
        <input type="text" id="bname" name="staffName" value="${requestScope.updateStaff.staffName}" lay-verify="title" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">联系方式</label>
      <div class="layui-input-block" style="width: 60%;">
        <input type="text" id="bphone" name="phone" value="${requestScope.updateStaff.phone}" lay-verify="title" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">所属部门</label>
      <div class="layui-form-item layui-inline" style="width: 60%;">
        <select name="deptId">
          <option value="">部门</option>
          <c:forEach items="${requestScope.deptList}" var="dept">
            <option value="${dept.id}"
                    <c:if test="${requestScope.updateStaff.deptId == dept.id }">selected="selected"</c:if>
            >${dept.deptName}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div>
      <div style="position:fixed;bottom:0; right:10px; text-align: right;margin-right:10px; width: 100%; background-color: #f9f9f9; height: 50px; line-height: 45px; margin-right: 5px">
        <button class="layui-btn layui-btn-normal" onclick="push()" >确认</button>
        <button class="layui-btn layui-btn-normal" onclick="exit()" >取消</button>
      </div>
    </div>
  </form>
</div>
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery-1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
  var form = document.getElementsByTagName('form')[0];
  form.addEventListener('submit',function(e){
    e.preventDefault();
  });
  layui.use('form', function(){
    var form = layui.form;
    form.render();
  });
  function push(){
    $.ajax({
      url:"StaffServlet?method=updateStaff",
      type: "POST",
      data:$("form").serialize(),
      success:function (str){
        if(str == '1') alert("修改成功");
        else alert("修改失败");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);//关闭当前页
        parent.location.reload();
      },
      error:function (){

      },
    });

  }
  function exit(){

  }
</script>
</body>
</html>
