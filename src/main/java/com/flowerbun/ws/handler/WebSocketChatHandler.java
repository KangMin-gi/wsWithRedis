package com.flowerbun.ws.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowerbun.ws.chatmessage.ChatMessage;
import com.flowerbun.ws.chatroom.ChatRoomManager;
import com.flowerbun.ws.chatservice.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

  private final ObjectMapper objectMapper;
  private final ChatService chatService;

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("payload {}", payload);

    ChatMessage chatMessage = this.objectMapper.readValue(payload, ChatMessage.class);
//    this.chatService.joinChatRoom(session, chatMessage);
    this.chatService.sendMessage(session, chatMessage);

//    super.handleTextMessage(session, message);
  }
}
