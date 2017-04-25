<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/3/28
  Time: 11:17
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
<body>
<script>
    var path = '<%=basePath%>';
    var userId = 'lys';
    if(userId==-1){
        window.location.href="<%=basePath%>";
    }
    var jspCode = userId+"_AAA";
    var websocket;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);
    } else {
        websocket = new SockJS("http://" + path + "wsMy/sockjs?jspCode=" + jspCode);
    }
    websocket.onopen = function(event) {
        console.log("WebSocket:已连接");
        console.log(event);
    };
    websocket.onmessage = function(event) {
        var data = JSON.parse(event.data);
        console.log("WebSocket:收到一条消息-norm", data);
        alert("WebSocket:收到一条消息");
    };
    websocket.onerror = function(event) {
        console.log("WebSocket:发生错误 ");
        console.log(event);
    };
    websocket.onclose = function(event) {
        console.log("WebSocket:已关闭");
        console.log(event);
    }
</script>
</body>
</html>
