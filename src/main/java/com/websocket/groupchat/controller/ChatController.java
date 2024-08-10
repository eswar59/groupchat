package com.websocket.groupchat.controller;

import com.websocket.groupchat.model.ChatMessage;
import com.websocket.groupchat.model.MessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendChatMessageToTopic")
    @SendTo("/topic/public")
    public ChatMessage sendChatMessageToTopic(@Payload ChatMessage chat){
        return chat;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chat, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chat.getSender());
        return chat;
    }
}
