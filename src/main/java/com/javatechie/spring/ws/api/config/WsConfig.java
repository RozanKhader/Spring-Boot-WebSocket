package com.javatechie.spring.ws.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WsConfig extends WebSocketMessageBrokerConfigurationSupport implements WebSocketMessageBrokerConfigurer {


	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		// Increase buffers.
		// Too little buffers may result in fatal errros when transmitting relatively large messages.
		registry.setMessageSizeLimit(WebSocketTransportConfig.MAX_TEXT_MESSAGE_SIZE);
		registry.setSendBufferSizeLimit(WebSocketTransportConfig.BUFFER_SIZE);
		super.configureWebSocketTransport(registry);
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {

	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {

	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		return false;
	}
}
