package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.MessageService;
import com.example.MessageServiceImpl;
import com.example.SimpleMessage;

@Controller
public class SocketTesting {

	@Autowired
	private MessageService message_service = new MessageServiceImpl();
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public Collection<SimpleMessage> send(SimpleMessage message){
		this.message_service.create(message);
		
		return this.message_service.findAll();
	}
	
	@MessageMapping("/sendMessageHere/{id}")
	@SendTo("/message/recieveMessage/{id}")
	public SimpleMessage test_mapper(@DestinationVariable Long id, SimpleMessage message){
		this.message_service.create(message);
		
		return this.message_service.getMessageWithSomeId(id);
	}
}
