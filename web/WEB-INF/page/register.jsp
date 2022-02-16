<%--
  Created by IntelliJ IDEA.
  User: 17864
  Date: 2022/2/10
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/libs/particles/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/libs/sweetalert2/sweetalert2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/login.css">
</head>
<body>
<!-- particles.js container -->
<div id="particles-js"></div>
<div id="wrapper">
    <div>
        <img src="${pageContext.request.contextPath}/static/assets/img/logo.png" />
    </div>
    <nav class="switch_nav">
        <a href="javascript:;" id="switch_signup" class="switch_btn on">注册</a>
        <a href="PageServlet?method=loginPage" class="switch_btn">登陆</a>
        <div class="switch_bottom" id="switch_bottom"></div>
    </nav>
    <form onsubmit="return false">
        <ul class="group_input">
            <li style="margin-left: 13px">
                <input type="text" placeholder="姓名" class="name required" id="name" name="name"/>
            </li>
            <li style="margin-left: 13px">
                <input type="text" placeholder="手机号(仅支持中国大陆)" class="mobile required" id="mobile" name="user"/>
            </li>
            <li style="margin-left: 13px">
                <input type="password" placeholder="密码(不少于6位)" class="psd required" id="psd" name="psd"/>
            </li>
            <li style="margin-left: 13px">
                <input type="password" placeholder="确认密码(不少于6位)" class="psd required" id="repsd" name="psd"/>
            </li>
        </ul>
        <button type="submit" class="submit_btn" id="btnSubmit" onclick="register_btn()">注册</button>

        <span class="agreement-tip">点击「注册」按钮，即代表你同意<a href="javascript:;">《协议》</a></span>
    </form>
</div>
<script src="${pageContext.request.contextPath}/static/assets/libs/sweetalert2/sweetalert2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/particles/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/particles/js/app.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery-1.12.4/jquery.min.js"></script>
<script>
    var form = document.getElementsByTagName('form')[0];
    form.addEventListener('submit',function(e){
        e.preventDefault();
    });
</script>

<script>
    $(function(){
        //为表单元素添加失去焦点事件
        $("form :input").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            //验证姓名
            if($(this).is("#name")){
                var nameVal = $.trim(this.value);
                var regName = /[~#^$@%&!*()<>:;'"{}【】  ]/;
                if(nameVal == "" || nameVal.length < 2 || regName.test(nameVal)){
                    var errorMsg = " 姓名非空，长度2-20位，不包含特殊字符！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                } else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
            //验证手机号
            if($(this).is("#mobile")){
                var mobileVal = $.trim(this.value);
                var regMobile = /^1[3|4|5|7|8][0-9]{9}$/;
                if(mobileVal == "" || !regMobile.test(mobileVal)){
                    var errorMsg = " 请输入有效的11位手机号码！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                } else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
            //验证密码
            if($(this).is("#psd")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
            if($(this).is("#repsd")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
        }).keyup(function(){
            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
            $(this).triggerHandler("blur");
        }).focus(function(){
            $(this).triggerHandler("blur");
        });

        //点击重置按钮时，通过trigger()来触发文本框的失去焦点事件
        $("#btnSubmit").click(function(){
            //trigger 事件执行完后，浏览器会为submit按钮获得焦点
            $("form .required:input").trigger("blur");
            var numError = $("form .onError").length;
            if(numError){
                return false;
            }
        });
    })
    function register_btn(){
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var psd = $("#psd").val();
        var repsd = $("#repsd").val();

        console.info(mobile+ " " + psd);
        if(name =='' || mobile == '' ||  psd == ''){
            return;
        }
        if(psd != repsd){
            alert("两次密码不一致，请重新输入");
        }
        $.ajax({
            url: "StaffServlet?method=registerDeal",
            type: "POST",
            data: {"name":name,"moblie":mobile,"psd":psd},
            success:function (str){
                if(str == 1){
                    alert("注册成功");
                    window.location.href = "PageServlet?method=mainPage";
                }
                else{
                    alert("已存在相同用户");
                }
            },
            error:function (){
                alert("请求失败");
            }
        })
    }

</script>
</body>
</html>

