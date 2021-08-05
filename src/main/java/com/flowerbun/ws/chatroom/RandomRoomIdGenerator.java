package com.flowerbun.ws.chatroom;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RandomRoomIdGenerator implements RoomIdGenerator {

  @Override
  public String roomId() {
    return UUID.randomUUID().toString();
  }
}
