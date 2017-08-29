package com.niit.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
@ComponentScan(basePackages="com.niit")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void configureClientInboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	public void configureClientOutboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	public void configureMessageBroker(MessageBrokerRegistry configurer) {
		// TODO Auto-generated method stub
		System.out.println("Configure messageBrokerRegistory*****");
		configurer.enableSimpleBroker("/queue/","/topic/");
		configurer.setApplicationDestinationPrefixes("/app");
		
		
	}

	public boolean configureMessageConverters(List<MessageConverter> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public void configureWebSocketTransport(WebSocketTransportRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	public void registerStompEndpoints(StompEndpointRegistry registory) {
		// TODO Auto-generated method stub
		System.out.println("Register Stomp Endpoints***********");
		registory.addEndpoint("/chatting").withSockJS();
		
		
	}
	

}
