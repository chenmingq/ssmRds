<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/3/21
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>登录</title>
</head>
<body>
    <div class="head">
        <div>
            <form action="login.jsp" method="post">
                账户:<input type="text" class="userName" placeholder="邮箱号" >
                <br>
                密码:<input type="password" class="psd" placeholder="输入密码">
                <br>
                <input type="button" value="登录" class="loginBtn">
                <input type="reset" value="重置" class="resetBtn">
            </form>
        </div>

    </div>
</body>
</html>
