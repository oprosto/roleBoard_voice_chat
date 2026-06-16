package com.roleplace.voicechatservice;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SignalController {

    @MessageMapping("/signal")
    @SendTo("/topic/signals")
    public SignalMessage process(SignalMessage message) {

        System.out.println("Signal: " + message.getType());

        return message;
    }
}
