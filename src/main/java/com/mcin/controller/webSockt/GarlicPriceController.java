package com.mcin.controller.webSockt;

import com.google.gson.GsonBuilder;
import com.mcin.websocket.MyWebSocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Mcin on 2017/3/28.
 */
@Controller
public class GarlicPriceController {
//    @Resource
//    MyWebSocketHandler myWebSocketHandler;
//    @RequestMapping(value = "GarlicPriceController/testWebSocket", method ={RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public String testWebSocket() throws IOException {
//        myWebSocketHandler.sendMessageToJsp(new TextMessage(new GsonBuilder().create().toJson("\"number\":\""+"GarlicPriceController/testWebSocket"+"\"")), "AAA");
//        return "1";
//    }
}
