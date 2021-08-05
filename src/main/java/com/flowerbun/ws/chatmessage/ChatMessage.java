package com.flowerbun.ws.chatmessage;

import com.flowerbun.ws.exception.Common500Exception;
import com.flowerbun.ws.exception.Errors;
import lombok.Data;

@Data
public class ChatMessage {

  private String roomId;
  private String senderName;
  private String message;
  private MessageType messageType;

  public String helloMessage() {
    validHello();
    return senderName + " 님이 입장 하셨습니다.";
  }

  public void validHello() {
    if (messageType != MessageType.ENTER) {
      throw Common500Exception.of(Errors.WRONG_MESSAGE_TYPE);
    }
  }
}
