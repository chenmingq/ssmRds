<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/3/8
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/myCss/main.css" />
    <script src="${pageContext.request.contextPath}/view/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/js/myJS/md5.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/view/js/myJS/login.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/view/js/myJS/main.js" type="text/javascript" charset="utf-8"></script>
    <title>登录</title>
</head>

<body>
<div class="bg" id="bg"></div>
<div class="login-contain">
    <h1 class="contain-header">
        <span>Hello,</span>
        <span>Welcome back!</span>
    </h1>
    <div class="contain-body" id="containBody">
        <div class="body-check" style="text-align: center;position: absolute;z-index: 9;width: 100%;height: 100%;border-radius: 10px;background-color: #FFFFFF;">
            <select id="oSelect" style="width:60%;height: 50px;font-size: 20px;font-weight: bolder;margin-top: 50px;">
                <option selected="selected">请选择登录类型</option>
                <option>企业登陆</option>
                <option>个人登录</option>
            </select>
        </div>
        <form name="loginform" id="loginform" action="" method="post" class="body-form login" style="">
            <p class="form-register">
                <button type="button" id="oBack" class="loginBtn"></button>
                <span>登陆</span>
                <button class="return-btn" type="button">没有账户？注册</button>
            </p>
            <p class="form-log">
                <input type="text" class="loginName" id="username" placeholder="企业名称">
            </p>
            <p class="form-pwd">
                <input type="text" class="loginPwd" id="account" placeholder="简历账号：手机或邮箱">
            </p>
            <%--<p class="form-log">--%>
                <%--<input type="text" id="meLoginName" placeholder="企业名称">--%>
            <%--</p>--%>
            <%--<p class="form-pwd">--%>
                <%--<input type="text" id="account" placeholder="简历账号：手机或邮箱">--%>
            <%--</p>--%>
            <p class="form-checkbox" id="oRadio">
                <input type="radio" name="loginType" id="ceo" value="ceo" /><span>CEO</span>&nbsp;
                <input type="radio" name="loginType" id="hr" value="hr" /><span>HR</span>&nbsp;
                <input type="radio" name="loginType" id="cto" value="cto" /><span>CTO</span>&nbsp;
                <input type="radio" name="loginType" id="cpjl" value="cpjl" /><span>产品经理</span>&nbsp;
                <input type="radio" name="loginType" id="qita" value="qita" /><span>其他</span>
            </p>
            <p class="form-remeber">
                <label for="remeberme">
                    <input type="checkbox" name="remeberme" id="remeberme">
                    记住密码
                </label>
            </p>
            <p class="form-forget">
                <a href="" target="_blank">忘记密码？</a>
            </p>
            <p class="form-btn">
                <button type="button" id="userLogin" >登陆</button>
                <button type="button" id="oClear" >重置</button>
            </p>
        </form>

        <form name="registerform" id="registerform" action="" method="post" class="body-form register">
            <p class="form-login">
                <span>注册</span>
                <button class="return-btn" type="button">已有账户？登陆</button>
            </p>
            <p class="form-log">
                <input type="text" name="log" id="registerEmail" required placeholder="账户/邮箱">
            </p>
            <p class="form-pwd">
                <input type="password" name="pwd" id="registerPassWord" required placeholder="密码">
            </p>
            <p class="form-btn">
                <button type="button" id="registerBtn">注册</button>
            </p>
        </form>
    </div>

</div>
<script type="text/javascript">
    ;
    (function() {
        function $(id){
            return document.getElementById(id);
        }
        window.onload = function() {
            var flag = true,
                btns = document.getElementsByClassName('return-btn'),
                i = 0,
                l = btns.length,
                box = $('containBody');

            for(; i < l; i++) {
                btns[i].addEventListener('click', function() {
                    if(flag) {
                        box.style.transform = 'rotateY(180deg)';
                        flag = false;
                    } else {
                        box.style.transform = 'rotateY(0)';
                        flag = true;
                    }
                }, false);
            }
            $("oSelect").addEventListener('change',function(){
                if(this.options.selectedIndex==1){
                    this.parentNode.style.display='none';
                }else if(this.options.selectedIndex==2){
                    $("username").placeholder = "账户";
                    $("account").placeholder = "密码";

                    $("username").value = "";
                    $("account").value = "";
                    $("account").type ="password";

                    $("username").id = "meLoginName";
                    $("account").id = "meLoginPassWord";
//                    $('input:radio[name="loginType"]').attr(false);
                    $("oRadio").style.display=$("oClear").style.display='none';
                    this.parentNode.style.display='none';
                }
            },false);
            $("oClear").addEventListener('click',function(){
                $("username").value=$("account").value="";
//                $("username").value=$("userpwd").value="";
            },false);
            $("oBack").addEventListener('click',function(){
                $("oSelect").parentNode.style.display='block';
            },false);
        }
    })();
</script>
</body>
</html>