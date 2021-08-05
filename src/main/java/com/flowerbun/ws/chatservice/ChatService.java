package com.flowerbun.ws.chatservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowerbun.ws.chatmessage.ChatMessage;
import com.flowerbun.ws.chatroom.ChatRoom;
import com.flowerbun.ws.chatroom.ChatRoomManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

  private final ObjectMapper objectMapper;
  private final ChatRoomManager chatRoomManager;

  public ChatRoom joinChatRoom(WebSocketSession session, ChatMessage message) {
    return chatRoomManager.joinRoom(session, message);
  }

  public void sendMessage(WebSocketSession session, ChatMessage message) {
    String roomId = message.getRoomId();
    ChatRoom room = chatRoomManager.findRoom(roomId);
  }

}
