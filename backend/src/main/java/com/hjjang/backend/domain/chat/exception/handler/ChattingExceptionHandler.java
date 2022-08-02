package com.hjjang.backend.domain.chat.exception.handler;

import com.hjjang.backend.domain.chat.exception.*;
import com.hjjang.backend.global.response.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hjjang.backend.global.response.code.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ChattingExceptionHandler {

    // seller를 찾을 수 없음
    @ExceptionHandler(value = NotFoundSellerEntityException.class)
    public ResponseEntity<ErrorResponse> notFoundSellerEntityExceptionHandle(NotFoundSellerEntityException e) {
        final ErrorResponse response = ErrorResponse.of(SELLER_NOT_FOUND);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    // 자기가 만든 방에 들어갈 수 없음
    @ExceptionHandler(value = CannotCreateChatRoomBySelfException.class)
    public ResponseEntity<ErrorResponse> cannotCreateChatRoomBySelfExceptionHandle(CannotCreateChatRoomBySelfException e) {
        final ErrorResponse response = ErrorResponse.of(SELLER_NOT_FOUND);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    // 채팅방을 찾을 수 없음
    @ExceptionHandler(value = NotFoundChatRoomEntityException.class)
    public ResponseEntity<ErrorResponse> notFoundSellerEntityExceptionHandle(NotFoundChatRoomEntityException e) {
        final ErrorResponse response = ErrorResponse.of(CHATROOM_NOT_FOUND);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    // 이미 숨겨진 채팅방은 숨길 수 없음
    @ExceptionHandler(value = IsAlreadyHiddenChatRoomException.class)
    public ResponseEntity<ErrorResponse> notFoundSellerEntityExceptionHandle(IsAlreadyHiddenChatRoomException e) {
        final ErrorResponse response = ErrorResponse.of(CHATROOM_IS_ALREADY_HIDDEN);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    // 참여중인 채팅방이 없습니다.
    @ExceptionHandler(value = NotFoundChatRoomEntranceEntityException.class)
    public ResponseEntity<ErrorResponse> notFoundSellerEntityExceptionHandle(NotFoundChatRoomEntranceEntityException e) {
        final ErrorResponse response = ErrorResponse.of(CHATROOM_ENTRANCE_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
