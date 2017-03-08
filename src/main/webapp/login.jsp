<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/3/8
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link  rel="stylesheet" type="text/css" href="view/css/myCss/login.css" />
</head>
<body bgcolor="#7fffd4">
    <div class="main_login">
        <form class="login_form" method="post" action="">
            <div class="login_input">
                <div class="login_user">
                   用户名： <input type="text" class="user_input" placeholder="输入用户名" value="">
                </div>
                <div class="login_psd">
                   密&nbsp;&nbsp;码： <input type="password" class="psd_input" placeholder="输入密码" value="">
                </div>
                <div class="login_btn">
                    <input type="submit" class="login_button" value="登录">
                    <input type="reset" class="registered_btn" value="注册">
                </div>
            </div>
        </form>
    </div>
</body>
</html>
