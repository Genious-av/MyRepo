package application.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import model.Message;

@Controller
public class WebSocketController {
	
	 @MessageMapping("/sendMessage")
	 @SendTo("/topic/publicChat")
	 public Message sendMessage(@Payload Message chatMessage) {
	     return chatMessage;
	 }
}
