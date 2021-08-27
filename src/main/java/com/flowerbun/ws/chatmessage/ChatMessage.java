package com.flowerbun.ws.chatmessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessage {

  private String roomId;
  private String senderName;
  private String message;
  private MessageType messageType;

  public String genMessage() {
    if (this.isJoin()) {
      return this.helloMessage();
    }
    return this.message;
  }

  public boolean isJoin() {
    return this.messageType == MessageType.ENTER;
  }

  private String helloMessage() {
    return senderName + " 님이 입장 하셨습니다.";
  }
}
