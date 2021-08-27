package com.flowerbun.ws.chat;

import com.flowerbun.ws.chatmessage.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatWsController {

  private final SimpMessageSendingOperations messageTemplate;

  @MessageMapping("/chat/message")
  public void message(ChatMessage message) {
    this.messageTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}
