package com.flowerbun.ws.chatroom;

import lombok.Getter;

@Getter
public class ChatRoom {

  private String roomId;
  private String name;

  public ChatRoom(String roomId, String name) {
    this.roomId = roomId;
    this.name = name;
  }
}
