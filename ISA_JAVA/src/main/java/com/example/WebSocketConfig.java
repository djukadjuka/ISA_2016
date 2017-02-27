package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		
		//carry messages to the clinet destinations prefixed with "/topic"
		config.enableSimpleBroker("/topic","/message","/deliveries");
		
		//"/app" prefix to filter destinations targeting application annotated methods
		//via @MessageMapping
		config.setApplicationDestinationPrefixes("/app");
	};
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		//register endpoint 
		//this endpoint when prefixed with "/app" is the endpoint that the 
		//ChatController.send() method is mapped to handle
		registry.addEndpoint("/chat","/sendMessage/{id}","/testDeliveries/{id}").setAllowedOrigins("http://localhost:4200").withSockJS();
	}

}
