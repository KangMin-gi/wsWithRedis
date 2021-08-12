package com.flowerbun.ws.chatroom;

import com.flowerbun.ws.chatmessage.ChatMessage;
import com.flowerbun.ws.exception.Common500Exception;
import com.flowerbun.ws.exception.Errors;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Getter
public class ChatRoom {

  private String roomId;
  private String name;
  private Set<WebSocketSession> sessions = new HashSet<>();

  public ChatRoom(String roomId, String name) {
    this.roomId = roomId;
    this.name = name;
  }

  public void joinUser(WebSocketSession session, ChatMessage chatMessage) {
    this.sessions.add(session);
    this.sendAll(chatMessage.helloMessage());
  }

  public void send(WebSocketSession session, ChatMessage message) {
    if (!sessions.contains(session)) {
      Common500Exception.throwException(Errors.WRONG_ROOM_ID);
    }
    this.sendAll(message.getMessage());
  }

  public boolean existUser(WebSocketSession session) {
    return this.sessions.contains(session);
  }

  private void sendAll(String message) {
    TextMessage textMessage = new TextMessage(message);
    this.sessions.forEach(session -> {
      try {
        session.sendMessage(textMessage);
      } catch (IOException ignore) {
      }
    });
  }
}
