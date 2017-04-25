<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/3/25
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户上传图片页面</title>
    <base href="<%=basePath%>">
    <script src="${pageContext.request.contextPath}/view/js/jquery.min.js"></script>
    <script type="${pageContext.request.contextPath}/view/js/myJS/img.js"></script>
    <style type="text/css">
        #pic {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin: 20px auto;
            cursor: pointer;
        }
    </style>
</head>
<body>
<center>
    <form action="${pageContext.request.contextPath}/up/threeFile.json" method="post" enctype="multipart/form-data">
        <img id="pic" src="">
        <input id="upload"  type="file" name="file" value="" accept="image/*"  style="display: none"  multiple="multiple" /><br />
        <%--<input type="file"  name="file" value=""accept="image/*" multiple="multiple" /><br />
        <input type="file" name="file" value="" accept="image/*" multiple="multiple" /><br />--%>
        <input  type="submit" value="上 传" />
    </form>
    <h5>上传结果：</h5>

    <c:forEach items="${fileList}" var="imagename">
        <img alt="暂无图片" src="${imagename}" />	<br/>
    </c:forEach>



</center>
</body>
</html>
