package com.mcin.websocket;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * Created by Mcin on 2017/3/28.
 */
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Resource
    MyWebSocketHandler handler;
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
// TODO Auto-generated method stub
        registry.addHandler(handler, "/wsMy").addInterceptors(new HandShake());
        registry.addHandler(handler, "/wsMy/sockjs").addInterceptors(new HandShake()).withSockJS();
    }
}
