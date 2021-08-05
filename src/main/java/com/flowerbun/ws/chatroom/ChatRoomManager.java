package com.flowerbun.ws.chatroom;

import com.flowerbun.ws.chatmessage.ChatMessage;
import com.flowerbun.ws.exception.Common500Exception;
import com.flowerbun.ws.exception.Errors;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatRoomManager {

  private final Map<String, ChatRoom> chatRoomMap;
  private final RoomIdGenerator roomIdGenerator;

  public ChatRoomManager(RoomIdGenerator roomIdGenerator) {
    this.roomIdGenerator = roomIdGenerator;
    this.chatRoomMap = new LinkedHashMap<>();
  }

  public List<ChatRoom> getAllRooms() {
    return new ArrayList<>(this.chatRoomMap.values());
  }

  public ChatRoom findRoom(String roomId) {
    return this.chatRoomMap.get(roomId);
  }

  public ChatRoom joinRoom(WebSocketSession session, ChatMessage chatMessage) {
    String roomId = chatMessage.getRoomId();
    ChatRoom room = this.findRoom(roomId);
    room.joinUser(session, chatMessage);
    return room;
  }

  public ChatRoom createRoom(String name) {
    String roomId = this.roomIdGenerator.roomId();
    ChatRoom chatRoom = new ChatRoom(roomId, name);
    this.addRoom(chatRoom);
    return chatRoom;
  }

  private void addRoom(ChatRoom chatRoom) {
    String roomId = chatRoom.getRoomId();
    validRoomId(roomId);
    this.chatRoomMap.put(roomId, chatRoom);
  }

  private void validRoomId(String roomId) {

    if (ObjectUtils.isEmpty(roomId)) {
      Common500Exception.throwException(Errors.EMPTY_ROOM_ID);
    }

    if (chatRoomMap.containsKey(roomId)) {
      Common500Exception.throwException(Errors.CREATE_EXIST_ROOM_ID);
    }
  }

}
