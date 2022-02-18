<%--
  Created by IntelliJ IDEA.
  User: 17864
  Date: 2022/2/17
  Time: 20:23
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
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block"style="width: 60%;">
                <input type="text" id="staffName" name="staffName"  lay-verify="title" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登陆密码</label>
            <div class="layui-input-block" style="width: 60%;">
                <input type="password" id="pass" name="pass"  lay-verify="title" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block" style="width: 60%;">
                <input type="password" id="repass" name="repass"  lay-verify="title" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-block" style="width: 60%;">
                <input type="text" id="phone" name="phone"  lay-verify="title" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属部门</label>
            <div class="layui-form-item layui-inline" style="width: 60%;">
                <select name="deptId">
                    <option value="">请选择部门</option>
                    <c:forEach items="${requestScope.deptList}" var="dept">
                        <option value="${dept.id}">${dept.deptName}</option>
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
    $(function(){
        //为表单元素添加失去焦点事件
        $("form :input").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            //验证手机号
            if($(this).is("#phone")){
                var mobileVal = $.trim(this.value);
                // var regMobile = /^1[3|4|5|7|8][0-9]{9}$/;
                if(mobileVal == ""){
                    var errorMsg = " 请输入手机号！";
                    $parent.append("<span class='msg onError' style='color: red'>" + errorMsg + "</span>");
                } else if(!(/^1[0-9]{10}$/.test(mobileVal))){
                    var errorMsg = " 请输入正确的手机号！";
                    $parent.append("<span class='msg onError' style='color: red'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'style='color: green'>" + okMsg + "</span>");
                }
            }
            //验证密码
            if($(this).is("#pass")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<span class='msg onError'style='color: red'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'style='color: green'>" + okMsg + "</span>");
                }
            }
            if($(this).is("#repass")){
                var psdVal = $.trim(this.value);
                var psd = $("#pass").val();
                if(psd != psdVal){
                    var errorMsg = " 两次输入的密码不一致";
                    $parent.append("<span class='msg onError'style='color: red'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'style='color: green'>" + okMsg + "</span>");
                }
            }
        }).keyup(function(){
            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
            $(this).triggerHandler("blur");
        }).focus(function(){
            $(this).triggerHandler("blur");
        });
    })
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
            url:"StaffServlet?method=addStaff",
            type: "POST",
            data:$("form").serialize(),
            success:function (str){
                if(str == '1') alert("添加成功");
                else alert("添加失败");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);//关闭当前页
                parent.location.reload();
            },
            error:function (){

            },
        });

    }
    function exit(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
</script>
</body>
</html>

