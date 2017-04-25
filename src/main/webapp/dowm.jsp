<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/3/25
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- 遍历Map集合 -->
<!-- 遍历Map集合 -->
<%--<c:forEach var="me" items="${fileNameMap}">--%>
    <%--<c:url value="/up/downFile.json" var="downurl">--%>
        <%--<c:param name="filename" value="${me.key}"></c:param>--%>
    <%--</c:url>${me.value}--%>
    <%--<img src="${me.value}">--%>
    <%--<a href="${downurl}">下载</a>--%>
    <%--<br/>--%>
<%--</c:forEach>--%>

<c:forEach var="me" items="${fileModelList}">
    <img src="${pageContext.request.contextPath}${me.fileName}">
    <c:url value="/up/downFile.json" var="downurl">
    <c:param name="filename" value="${me.fileName}"></c:param>
    </c:url>${me.shouImgName}
    <br/>
    <a href="${downurl}">下载</a>
</c:forEach>

</body>
</html>
