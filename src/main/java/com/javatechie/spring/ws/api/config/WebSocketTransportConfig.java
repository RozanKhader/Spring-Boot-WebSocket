package com.javatechie.spring.ws.api.config;

import org.apache.logging.log4j.LogManager;
import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.logging.Logger;

/**
 * Created by Win10 on 2/25/21.
 */
@Configuration
@EnableWebSocket
public class WebSocketTransportConfig implements WebSocketConfigurer {
    // Important web socket setup. If big message is coming through, it may overflow the buffer and this will lead in disconnect.
    // All messages that are coming through normally (including snapshots) must be order of magnitude smaller, or connection will be broken
    // sometimes
    // There is also MaxBinaryMessageSize that we do not employ as we use Stomp, but for completeness it is also set to same values.
    // Javadoc http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/socket/adapter/jetty/JettyWebSocketSession.html#setTextMessageSizeLimit-int-

    public static final int MAX_TEXT_MESSAGE_SIZE = 2048000; // 2 Megabytes.
    public static final int BUFFER_SIZE = MAX_TEXT_MESSAGE_SIZE * 5;



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    }


}