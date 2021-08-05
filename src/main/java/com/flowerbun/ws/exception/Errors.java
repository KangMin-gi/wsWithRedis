package com.flowerbun.ws.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

  WRONG_MESSAGE_TYPE("잘못된 데이터 타입입니다."),
  CREATE_EXIST_ROOM_ID("이미 존재하는 방 번호 입니다."),
  EMPTY_ROOM_ID("방 번호가 존재하지 않습니다."),
  WRONG_ROOM_ID("잘못된 방 정보 입니다."),
  PADDING("PADDING");

  private final String errorMessage;
}
