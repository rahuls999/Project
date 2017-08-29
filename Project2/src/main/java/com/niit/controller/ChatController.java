package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.niit.model.Chat;

@RestController
public class ChatController {
	private static final Log logger=LogFactory.getLog(ChatController.class);
	private final SimpMessagingTemplate messagingTemplate;
	private List<String> users=new ArrayList<String>();
	@Autowired
	public ChatController(SimpMessagingTemplate messagingTemplate){
		this.messagingTemplate=messagingTemplate;
	}
@MessageMapping(value="/chatting")

public void  ChatRecived(Chat chat){
	if("all".equals(chat.getTo())){
		messagingTemplate.convertAndSend("/topic/message",chat);
		//logger.info("Message sent*******");
	}
	else{
		
		messagingTemplate.convertAndSend("/topic/message/" +chat.getTo(),chat);
		messagingTemplate.convertAndSend("/topic/message/" +chat.getFrom(),chat);
		
	}
}
}


