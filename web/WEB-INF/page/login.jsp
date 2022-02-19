<%--
  Created by IntelliJ IDEA.
  User: 17864
  Date: 2022/2/10
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/libs/particles/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/login.css" />
    <style>
        #canvas {
        /*float: ;*/
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 5px;
        cursor: pointer;
        }
        * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        }
    </style>
</head>
<body>
<!-- particles.js container -->
<div id="particles-js"></div>
<div id="wrapper">
    <div>
        <img src="${pageContext.request.contextPath}/static/assets/img/logo.png" style=""/>
        <h2>与世界分享你的知识、经验和见解</h2>
    </div>
    <nav class="switch_nav">
        <a href="PageServlet?method=registerPage" class="switch_btn" >注册</a>
        <a href="javascript:;" id="switch_login" class="switch_btn on">登陆</a>
        <div class="switch_bottom" id="switch_bottom"></div>
    </nav>
    <div id="login">
        <form style="margin-left: 20px" onsubmit="return false">
            <ul class="group_input">
                <li style="margin-left: 3px">
                    <input type="text" class="mobile required" id="mobile" name="mobile" placeholder="手机号或邮箱" />
                </li>
                <li style="margin-left: 3px">
                    <input type="password" class="psd required" id="psd" name="psd" placeholder="密码" />
                </li>
                <li style="margin-left: 3px">
                    <input type="text" id="inputcode" placeholder="请输入验证码（不区分大小写）" >
                    <input type="text" id="code" value="" style="display: none">
                    <canvas id="canvas" width="100" height="43"></canvas>
                </li>
            </ul>
            <button type="submit" class="submit_btn" id="btnSubmit" onclick="login_btn()">登陆</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/assets/libs/jquery-1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/particles/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/libs/particles/js/app.js"></script>

<script>
    $(function(){
        var show_num = [];
        draw(show_num);
        $("#code").val(show_num);
        $("#canvas").on('click',function(){
            $("#code").val(show_num);
            draw(show_num);
        })
    })

    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度

        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    $(function(){
        //为表单元素添加失去焦点事件
        $("form :input").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            //验证手机号
            if($(this).is("#mobile")){
                var mobileVal = $.trim(this.value);
                // var regMobile = /^1[3|4|5|7|8][0-9]{9}$/;
                if(mobileVal == ""){
                    var errorMsg = " 请输入手机号或邮箱！";
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
        }).keyup(function(){
            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
            $(this).triggerHandler("blur");
        }).focus(function(){
            $(this).triggerHandler("blur");
        });
    })
    function login_btn(){
        var mobile = $("#mobile").val();
        var psd = $("#psd").val();
        var inputcode = $("#inputcode").val().toLowerCase();
        var code = $("#code").val().split(",");
        for (let i = 0; i < code.length; i++) {
            if(code[i] != inputcode[i]){
                $("#inputcode").val("");
                var show_num = [];
                draw(show_num);
                $("#code").val(show_num);
                return;
            }
        }
        if(mobile == '' ||  psd == ''){
            return;
        }
        $.ajax({
            url: "StaffServlet?method=loginCheck",
            type: "POST",
            data: {"phone":mobile,"pass":psd},
            success:function (str){
                if(str == 1){
                    window.location.href = "PageServlet?method=mainPage";
                }
                else{
                    alert("登录失败，请重新登录");
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

