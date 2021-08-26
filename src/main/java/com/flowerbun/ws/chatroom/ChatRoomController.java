package com.flowerbun.ws.chatroom;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("chatroom")
public class ChatRoomController {

  private final ChatRoomManager chatRoomManager;

  @PostMapping
  public ResponseEntity<ChatRoomResponse> createRoom(@RequestParam String name) {
    ChatRoom room = this.chatRoomManager.createRoom(name);
    return new ResponseEntity<>(new ChatRoomResponse(room), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ChatRoomResponse>> roomList() {
    List<ChatRoomResponse> list = this.chatRoomManager.getAllRooms()
        .stream()
        .map(ChatRoomResponse::new)
        .collect(Collectors.toList());



    return new ResponseEntity<>(list, HttpStatus.OK);
  }
 // TODO : 메세지브로커 관련 코드 추가 필요
}
