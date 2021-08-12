package com.flowerbun.ws.chatroom;

import lombok.Getter;

@Getter
public class ChatRoomResponse {

  private String roomId;
  private String roomName;

  public ChatRoomResponse(ChatRoom chatRoom) {
    this.roomId = chatRoom.getRoomId();
    this.roomName = chatRoom.getName();
  }
}
