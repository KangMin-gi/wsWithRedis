package com.flowerbun.ws.chatservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowerbun.ws.chatmessage.ChatMessage;
import com.flowerbun.ws.chatroom.ChatRoom;
import com.flowerbun.ws.chatroom.ChatRoomManager;
import com.flowerbun.ws.exception.Common500Exception;
import com.flowerbun.ws.exception.Errors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

  private final ObjectMapper objectMapper;
  private final ChatRoomManager chatRoomManager;

  public void sendMessage(WebSocketSession session, ChatMessage message) {
    String roomId = message.getRoomId();
    ChatRoom room = chatRoomManager.findRoom(roomId);

    if (ObjectUtils.isEmpty(room)) {
      Common500Exception.throwException(Errors.WRONG_ROOM_ID);
    }

    boolean chatUser = room.existUser(session);
    if (!chatUser) {
      room.joinUser(session, message);
      return;
    }
    room.send(session, message);

  }

}
