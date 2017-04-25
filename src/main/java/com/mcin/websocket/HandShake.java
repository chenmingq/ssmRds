package com.mcin.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Created by Mcin on 2017/3/28.
 */
public class HandShake implements HandshakeInterceptor {


    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // TODO Auto-generated method stub
        String jspCode = ((ServletServerHttpRequest) request).getServletRequest().getParameter("jspCode");
        // 标记用户
        //String userId = (String) session.getAttribute("userId");
        if(jspCode!=null){
            attributes.put("jspCode", jspCode);
        } else {
            return false;
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // TODO Auto-generated method stub
    }
}
